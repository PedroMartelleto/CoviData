package com.dashboard.components.graphs;

import java.util.ArrayList;
import java.util.Map;

import com.dashboard.data.importer.ChartsImporter;
import com.dashboard.data.model.LineChartDataModel;
import com.sun.javafx.scene.paint.GradientUtils.Point;

import javafx.scene.chart.XYChart;

public class TimeSeriesDataProvider {
	private static LineChartDataModel data;
	private static ChartsImporter importer = new ChartsImporter();
	private static ArrayList<XYChart.Data<String, Number>> dummyData = new ArrayList<>();
	
	static {
		dummyData.add(new XYChart.Data<String, Number>("01/01", 10));
		dummyData.add(new XYChart.Data<String, Number>("02/01", 20));
		dummyData.add(new XYChart.Data<String, Number>("03/01", 100));
	}

	public static void vaccinations(XYChart<String, Number> chart) {
		data = importer.GetVaccinationsLineChart();
		chart.setAnimated(true);
		provideLabels(chart, "Vacinas aplicadas, acumuladas por dia", "Data", "Vacinas aplicadas");
		provideSeries(chart, "Vacinas aplicadas", data.GetDots());
	}
	
	private static void provideLabels(XYChart<String, Number> destChart, String title, String xLabel, String yLabel) {
		destChart.getXAxis().setLabel(xLabel);
		destChart.getXAxis().setLabel(yLabel);
		destChart.setTitle(title);
	}

	private static void provideSeries(XYChart<String, Number> destChart, String name,
			Map<String, Integer> seriesData) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		for(Map.Entry<String, Integer> point:  seriesData.entrySet()) {
			series.getData()
			.add(new XYChart.Data<String, Number>(point.getKey(), point.getValue()));
		}
		series.setName(name);
		destChart.getData().add(series);
	}

}
