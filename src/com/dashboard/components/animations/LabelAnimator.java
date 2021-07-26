package com.dashboard.components.animations;

import java.util.HashMap;

import com.dashboard.utils.MathUtils;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Animates labels to some target value.
 * @author Pedro
 */
public class LabelAnimator extends AnimationTimer {
	/**
	 * Data needed for animations.
	 */
	private class AnimationData {
		public DisplayNumber start; // Start value
		public DisplayNumber target; // End value
		public float time = 0.0f; // 0 to 1, current progress in the animation
		public float animSpeed; // Speed of the animation
		public boolean isPlaying = true; // True if the animation is being played
		
		public AnimationData(DisplayNumber target, float animSpeed) {
			start = DisplayNumber.derivedFrom(target, 0);
			this.target = target;
			this.animSpeed = animSpeed;
		}
		
		public DisplayNumber getCurrent() {
			return DisplayNumber.derivedFrom(target, MathUtils.lerp(start.getValue(), target.getValue(), MathUtils.smoothStep(time)));
		}
	}
	
	private HashMap<Label, AnimationData> labelsAnimDataMap = new HashMap<>();
	private long previousTime = 0;
	
	/**
	 * Adds a label to the list of labels animated by this class.
	 * @param label a jfx label.
	 * @param digitsCount number of decimal digits to display.
	 * @param suffix string appended to the end of the value.
	 * @param animSpeed speed of the animation.
	 */
	public void addLabel(Label label, int digitsCount, String suffix, float animSpeed) {
		labelsAnimDataMap.put(label, new AnimationData(new DisplayNumber(0, digitsCount, suffix), animSpeed));
	}
	
	/**
	 * Smoothly transitions the value of value from the current value to the given target.
	 * @param label The label to set the value.
	 * @param target The end value of the animation.
	 */
	public void setLabelTarget(Label label, DisplayNumber target) {
		AnimationData oldAnim = labelsAnimDataMap.get(label);
		AnimationData anim = new AnimationData((DisplayNumber) target.clone(), oldAnim.animSpeed);
		anim.isPlaying = true;
		anim.start = oldAnim.getCurrent();
		anim.time = 0.0f;
		labelsAnimDataMap.put(label, anim);
	}
	
	/**
	 * Animates all labels from start to target.
	 */
	@Override
	public void handle(long now) {
		if (previousTime == 0) {
			previousTime = now;
			return;
		}
		
		for (HashMap.Entry<Label, AnimationData> entry : labelsAnimDataMap.entrySet()) {
			// For each label and animation currently being played...
			Label label = entry.getKey();
			AnimationData anim = entry.getValue();

			if (!anim.isPlaying) continue;
			
			anim.time += anim.animSpeed;
			DisplayNumber current = anim.getCurrent();			
	        label.setText(current.toString());
		}
		
        previousTime = now;
	}
}
