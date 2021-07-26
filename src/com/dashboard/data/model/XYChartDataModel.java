package com.dashboard.data.model;

import java.util.ArrayList;

import javafx.scene.chart.XYChart.Data;

/**
 * Data model for XY charts.
 */
public class XYChartDataModel {
	public ArrayList<Data<String, Number>> points = new ArrayList<Data<String, Number>>();
	
	/**
	 * Adds a point to this chart.
	 * @param X point x value.
	 * @param Y point y value.
	 */
	public final void addPoint(String X, Number Y) {
		points.add(new Data<String, Number>(X, Y));
	}
	
	/**
	 * Returns a list of points in this data model.
	 * @return
	 */
	public final ArrayList<Data<String, Number>> getPoints() {
		return points;
	}
}
