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
	
	private static float maxSpeed = 2000.0f;

	/**
	 * Data needed for smooth damp.
	 */
	private class AnimationData {
		public DisplayNumber start;
		public DisplayNumber target;
		public float time = 0.0f;
		public float animSpeed;
		public boolean isPlaying = true;
		
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
	
	public void addLabel(Label label, int digitsCount, String suffix, float animSpeed) {
		labelsAnimDataMap.put(label, new AnimationData(new DisplayNumber(0, digitsCount, suffix), animSpeed));
	}
	
	public void setLabelTarget(Label label, DisplayNumber target) {
		AnimationData oldAnim = labelsAnimDataMap.get(label);
		AnimationData anim = new AnimationData((DisplayNumber) target.clone(), oldAnim.animSpeed);
		anim.isPlaying = true;
		anim.start = oldAnim.getCurrent();
		anim.time = 0.0f;
		labelsAnimDataMap.put(label, anim);
	}
	
	/**
	 * Smooth damps all labels from start to target.
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
