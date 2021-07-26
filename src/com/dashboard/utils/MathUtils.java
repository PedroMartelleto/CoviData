package com.dashboard.utils;

/**
 * Math utils used during animations.
 */
public class MathUtils {
	/**
	 * Cubic Hermite interpolation after clamping.
	 * @param x the point to interpolate
	 * @return the interpolated value
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
	 * @param min minimum point
	 * @param max maximum point
	 * @param t time.
	 * @return the lerped value
	 */
	public static float lerp(float min, float max, float t) {
		return min + (max - min) * t;
	}
}
