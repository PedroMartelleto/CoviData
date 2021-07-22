package com.dashboard.components.animations;

import java.util.HashMap;

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
		public DisplayNumber current;
		public DisplayNumber target;
		public float velocity = 0.0f;
		public float animSpeed;
		public boolean isPlaying = true;
		
		public AnimationData(DisplayNumber target, float animSpeed) {
			current = DisplayNumber.derivedFrom(target, 0);
			this.target = target;
			this.animSpeed = animSpeed;
		}
	}
	
	private HashMap<Label, AnimationData> labelsAnimDataMap = new HashMap<>();
	private long previousTime = 0;
	
	public void addLabel(Label label, int digitsCount, String suffix, float animSpeed) {
		labelsAnimDataMap.put(label, new AnimationData(new DisplayNumber(0, digitsCount, suffix), animSpeed));
	}
	
	public void setLabelTarget(Label label, DisplayNumber target) {
		AnimationData anim = labelsAnimDataMap.get(label);
		anim.isPlaying = true;
		anim.current = DisplayNumber.derivedFrom(target, anim.current.getValue());
		anim.target = target;
		anim.velocity = 0.0f;
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
			
			// Performs a smooth damp
			final float smoothTime = 0.0001f;
			float deltaTime = (float)(now - previousTime) * (float)1e-6 * anim.animSpeed;
						
			float output = 0f;
	
	        float omega = 2.0f / smoothTime;
	        float x = omega * deltaTime;
	        float exp = 1F / (1F + x + 0.48F * x * x + 0.235F * x * x * x);
	
	        // Clamps maximum speed
	        float change = Math.min(anim.current.getValue() - anim.target.getValue(), maxSpeed * smoothTime);
	        float originalTo = anim.target.getValue();
	
	        anim.target.setValue(anim.current.getValue() - change);
	
	        float temp = (anim.velocity + omega * change) * deltaTime;
	        anim.velocity = (anim.velocity - omega * temp) * exp;
	
	        output = anim.target.getValue() + (change + temp) * exp;
	
	        // Prevents overshooting
	        float origMinusCurrent = originalTo - anim.current.getValue();
	        float outMinusOrig = output - originalTo;
	
	        if (origMinusCurrent * outMinusOrig > 0) {
	            output = originalTo;
	            anim.velocity = (output - originalTo) / deltaTime;
	        }
	        
	        // Signal that we are done if we have reach the goal
	        if (anim.target.getValue() == output) {
	        	anim.isPlaying = false;
	        }
	        
	        anim.current.setValue(output);
	        label.setText(anim.current.toString());
		}
		
        previousTime = now;
	}
}
