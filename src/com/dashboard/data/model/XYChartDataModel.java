package com.dashboard.data.model;

import java.util.ArrayList;

import javafx.scene.chart.XYChart.Data;

/**
 * Data model for XY charts.
 */
public class XYChartDataModel {
	public ArrayList<Data<String, Number>> points = new ArrayList<Data<String, Number>>();
	
	public final void addPoint(String X, Number Y) {
		points.add(new Data<String, Number>(X, Y));
	}
	
	public final ArrayList<Data<String, Number>> getPoints() {
		return points;
	}
}
