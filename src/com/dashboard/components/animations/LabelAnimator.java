package com.dashboard.components.animations;

import java.util.ArrayList;

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
		public final Label label;
		public float velocity = 0.0f;
		public float animSpeed;
		
		public AnimationData(DisplayNumber target, Label label, float animSpeed) {
			current = DisplayNumber.derivedFrom(target, 0);
			this.label = label;
			this.target = target;
			this.animSpeed = animSpeed;
		}
	}
	
	private ArrayList<AnimationData> labelsAnimData = new ArrayList<>();
	private long previousTime = 0;
	
	public void addLabel(Label label, DisplayNumber target, float animSpeed) {
		labelsAnimData.add(new AnimationData(target, label, animSpeed));
	}
	
	/**
	 * Smooth damps from start (0) to target.
	 */
	@Override
	public void handle(long now) {
		if (previousTime == 0) {
			previousTime = now;
			return;
		}
		
		for (AnimationData anim : labelsAnimData) {
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
	        
	        anim.current.setValue(output);
	        anim.label.setText(anim.current.toString());
		}
		
        previousTime = now;
	}
}
