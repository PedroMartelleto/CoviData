package com.dashboard.components.animations;

/**
 * Display number is a value nicely formatted for displaying in the GUI.
 */
public class DisplayNumber implements Cloneable {
	/**
	 * Creates a new DisplayNumber with the same properties as the given display number, but with a different value.
	 * @param value
	 * @param other
	 */
	public static DisplayNumber derivedFrom(DisplayNumber other, float value) {	
		return new DisplayNumber(value, other.digitsCount, new String(other.suffix));
	}
	
	private float value;
	private final int digitsCount;
	private final String suffix;
	
	/**
	 * Creates a new display number.
	 * @param value value of the number.
	 * @param digitsCount number of decimal digits to display.
	 * @param suffix suffix appended to the end of the number.
	 */
	public DisplayNumber(float value, int digitsCount, String suffix) {
		this.value = value;
		this.digitsCount = digitsCount;
		this.suffix = suffix;
	}
	
	/**
	 * Gets the value of this DisplayNumber.
	 * @return
	 */
	public float getValue() {
		return value;
	}
	
	/**
	 * Sets the value of this DisplayNumber.
	 * @return
	 */
	public void setValue(float newValue) {
		value = newValue;
	}
	
	/**
	 * Converts number to a string representation.
	 */
	@Override
	public String toString() {
		if (digitsCount == 0) {
			String wholeNumber = (int)value + suffix;
			for (int i = wholeNumber.length() - 3; i > 0; i -= 3) {
				wholeNumber = wholeNumber.substring(0, i) + "." + wholeNumber.substring(i);
			}
			return wholeNumber;
		}
		
		float factor = (float)Math.pow(10, digitsCount);		
		float formatted = Math.round(value * factor) / factor;		
		return formatted + suffix;
	}
	
	/**
	 * Clones this DisplayNumber.
	 */
	@Override
	public Object clone() {
		return new DisplayNumber(value, digitsCount, suffix);
	}
}
