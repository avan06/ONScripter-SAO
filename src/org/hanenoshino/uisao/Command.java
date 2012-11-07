package org.hanenoshino.uisao;

import org.hanenoshino.uisao.widget.VideoView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

/**
 * This class is the utility for UIHandler
 * Construction, argument assignment and some other works
 * @author trinity
 *
 */
public class Command {

	private static <T> T $(Object o) {
		return U.$(o);
	}

	// obj - Runnable
	public static final int RUN = 0;

	// obj - VideoView
	public static final int LOOP_VIDEO_PREVIEW = 13;

	// obj - VideoView
	public static final int RELEASE_VIDEO_PREVIEW = 14;

	// obj - GameAdapter
	public static final int GENERATE_TEST_DATA = 184;

	// obj - listview, arg1 - distance, arg2 - duration
	public static final int SCROLL_LIST_FOR_DISTANCE_IN_ANY_MILLIS = 10;

	// obj - MainActivity
	public static final int MAINACTIVITY_PLAY_VIDEO = 38;

	private static Handler Commander = new Handler() {

		public void handleMessage(Message msg) {
			VideoView videoview;
			switch(msg.what){
			case LOOP_VIDEO_PREVIEW:
				videoview = $(msg.obj);
				if(videoview.canSeekForward()) {
					videoview.seekTo(0);
				}else{
					videoview.setVideoURI(videoview.getVideoURI());
				}
				break;
			case SCROLL_LIST_FOR_DISTANCE_IN_ANY_MILLIS:
				ListView listview = $(msg.obj);
				listview.smoothScrollBy(msg.arg1, msg.arg2);
				break;
			case GENERATE_TEST_DATA:
				GameAdapter adapter = $(msg.obj);
				Tester.fillTestData(adapter);
				break;
			case MAINACTIVITY_PLAY_VIDEO:
				MainActivity mainactivity = $(msg.obj);
				mainactivity.playVideo();
				break;
			case RELEASE_VIDEO_PREVIEW:
				videoview = $(msg.obj);
				videoview.setVideoURI(null);
				break;
			default:
				if(msg.obj instanceof Runnable) {
					Runnable runnable = $(msg.obj);
					runnable.run();
				}
			}
		}

	};

	public static Command invoke(Runnable run) {
		Command cmd = new Command();
		cmd.msg.what = RUN;
		cmd.msg.obj = run;
		return cmd;
	}

	public static Command invoke(int progId) {
		Command cmd = new Command();
		cmd.msg.what = progId;
		return cmd;
	}

	public static void revoke(int progId) {
		Commander.removeMessages(progId);
	}

	public static void revoke(int progId, Object obj) {
		Commander.removeMessages(progId, obj);
	}

	private final Message msg;

	private Command() {
		msg = Message.obtain(Commander);
	}

	public Command of(Object obj) {
		msg.obj = obj;
		return this;
	}

	public Command args(int arg) {
		msg.arg1 = arg;
		return this;
	}

	public Command args(int arg1, int arg2) {
		msg.arg1 = arg1;
		msg.arg2 = arg2;
		return this;
	}

	public Command args(Bundle bundle) {
		msg.setData(bundle);
		return this;
	}
	
	public Command only() {
		if(msg.obj != null) {
			Commander.removeMessages(msg.what, msg.obj);
		}else{
			Commander.removeMessages(msg.what);
		}
		return this;
	}
	
	public Command exclude(int progId) {
		Commander.removeMessages(progId);
		return this;
	}

	public Command exclude(int progId, Object obj) {
		Commander.removeMessages(progId, obj);
		return this;
	}

	public Message getMessage() {
		return msg;
	}

	public void send() {
		Commander.sendMessage(msg);
	}

	public void sendAtTime(long timeMillis) {
		Commander.sendMessageAtTime(msg, timeMillis);
	}

	public void sendDelayed(long delay) {
		Commander.sendMessageDelayed(msg, delay);
	}

}
