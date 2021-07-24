package com.dashboard.components.animations;

public class DisplayNumber {
	/**
	 * Creates a new DisplayNumber with the same properties as the given display number, but with a different value.
	 * @param value
	 * @param other
	 */
	public static DisplayNumber derivedFrom(DisplayNumber other, float value) {	
		return new DisplayNumber(value, other.digitsCount, other.suffix);
	}
	
	private float value;
	private final int digitsCount;
	private final String suffix;
	
	public DisplayNumber(float value, int digitsCount, String suffix) {
		this.value = value;
		this.digitsCount = digitsCount;
		this.suffix = suffix;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float newValue) {
		value = newValue;
	}
	
	/**
	 * Converts number to a string representation.
	 */
	@Override
	public String toString() {
		float factor = (float)Math.pow(10, digitsCount);		
		float formatted = Math.round(value * factor) / factor;		
		return formatted + suffix;
	}
}