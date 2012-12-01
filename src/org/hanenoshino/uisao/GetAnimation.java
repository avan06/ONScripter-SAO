package org.hanenoshino.uisao;

import org.hanenoshino.uisao.anim.AnimationBuilder;
import org.hanenoshino.uisao.anim.AnimationListener;

import android.view.animation.Animation;

public class GetAnimation {

	public static class For {
		
		public static class MainInterface {

			public static Animation ToShowCover(AnimationListener listener) {
				Animation anim = 
						AnimationBuilder.create()
						// Change Fill Options
						.Fill.after(true).upward()
						// Set the valtype of the value to be inturrpted
						.valtype(Animation.RELATIVE_TO_SELF)
						// Add a Scale Animation
						.scale(0.5f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f).overshoot()
						.animateFor(300)
						// Add an Alpha Animation
						.alpha(0, 1).animateFor(300)
						// Build Animation
						.build(listener);
				return anim;
			}

			public static Animation ToHideCover(AnimationListener listener) {
				Animation anim = AnimationBuilder.create()
						// Change Fill Options
						.Fill.after(true).upward()
						// Change the global interpolator
						.accelerated(2)
						// Set the valtype of the value to be inturrpted
						.valtype(Animation.RELATIVE_TO_SELF)
						// Add a Scale Animation
						.scale(1.0f, 1.5f, 1.0f, 1.5f, 0.5f, 0.5f)
						.animateFor(300)
						// Add an Alpha Animation
						.alpha(1, 0).animateFor(300)
						// Build Animation
						.build(listener);
				return anim;
			}

			public static Animation ToShowBackground(AnimationListener listener) {
				Animation anim = AnimationBuilder.create()
						.alpha(0, 1).animateFor(1000).decelerated(1.5f)
						.build(listener);
				return anim;
			}

			public static Animation ToHideBackground(AnimationListener listener) {
				Animation anim = AnimationBuilder.create()
						.alpha(1, 0).animateFor(1000).accelerated(1.5f)
						.build(listener);
				return anim;
			}

			public static Animation ToShowVideoPlayerFrame(
					AnimationListener listener) {
				Animation anim = AnimationBuilder.create()
						.alpha(0, 1).animateFor(200).accelerated(1.5f)
						.build(listener);
				return anim;
			}

			public static Animation ToHideVideoPlayerFrame(
					AnimationListener listener) {
				Animation anim = AnimationBuilder.create()
						.alpha(1, 0).animateFor(200).accelerated(1.5f)
						.build(listener);
				return anim;
			}

		}
	}
}
