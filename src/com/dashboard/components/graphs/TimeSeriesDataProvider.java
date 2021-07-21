package com.dashboard.components.graphs;

import java.util.ArrayList;

import javafx.scene.chart.XYChart;

public class TimeSeriesDataProvider {
	private static ArrayList<XYChart.Data<String, Number>> dummyData = new ArrayList<>();
	
	static {
		dummyData.add(new XYChart.Data<String, Number>("01/01", 10));
		dummyData.add(new XYChart.Data<String, Number>("02/01", 20));
		dummyData.add(new XYChart.Data<String, Number>("03/01", 100));
	}

	public static void vaccinations(XYChart<String, Number> chart) {
		chart.setAnimated(true);
		provideLabels(chart, "Vacinas aplicadas, acumuladas por dia", "Data", "Vacinas aplicadas");
		provideSeries(chart, "Vacinas aplicadas", dummyData);
	}
	
	private static void provideLabels(XYChart<String, Number> destChart, String title, String xLabel, String yLabel) {
		destChart.getXAxis().setLabel(xLabel);
		destChart.getXAxis().setLabel(yLabel);
		destChart.setTitle(title);
	}

	private static void provideSeries(XYChart<String, Number> destChart, String name,
			ArrayList<XYChart.Data<String, Number>> seriesData) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.getData().addAll(seriesData);
		series.setName(name);
		destChart.getData().add(series);
	}

}
