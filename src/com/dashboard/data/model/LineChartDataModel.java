package com.dashboard.data.model;

import java.util.ArrayList;
import javafx.util.Pair;

public class LineChartDataModel {
	private ArrayList< Pair<Integer, Integer> > dots = new ArrayList< Pair<Integer, Integer> >();
	
	public final void AddDot(int X, int Y) {
		this.dots.add(new Pair<>(X, Y));
	}
	
	public final ArrayList< Pair<Integer, Integer> > GetDots() {
		return this.dots;
	}
}
