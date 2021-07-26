package com.dashboard.utils;

/**
 * Math utils used during animations.
 */
public class MathUtils {
	/**
	 * Cubic Hermite interpolation after clamping.
	 * @param x
	 * @return
	 */
	public static float smoothStep(float x) {
		if (x <= 0) return 0.0f;
		else if (x >= 1) return 1.0f;
		else {
			return 3 * x * x - 2 * x * x * x;
		}
	}
	
	/**
	 * Lerps from minimum to maximum.
	 * @param min
	 * @param max
	 * @param t Time.
	 * @return
	 */
	public static float lerp(float min, float max, float t) {
		return min + (max - min) * t;
	}
}
