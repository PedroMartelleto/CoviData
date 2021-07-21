package com.dashboard.data.model;

import java.util.TreeMap;
import java.util.Map;

public class LineChartDataModel {
	private Map<String, Integer> dots = new TreeMap<String, Integer>();
	
	public final void AddDot(String X, int Y) {
		this.dots.put(X, Y);
	}
	
	public final Map <String, Integer> GetDots() {
		return this.dots;
	}
}
