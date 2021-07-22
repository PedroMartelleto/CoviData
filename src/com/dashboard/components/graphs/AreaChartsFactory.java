package com.dashboard.components.graphs;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class AreaChartsFactory {
	private static AreaChart<String, Number> areaChart(String title, String xLabel, String yLabel) {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);

		AreaChart<String, Number> chart = new AreaChart<String, Number>(xAxis, yAxis);
		chart.setTitle(title);

		return chart;
	}

	public static AreaChart<String, Number> vaccinationsPerDay(String city) {
		AreaChart<String, Number> chart = areaChart("Numero acumulado de vacinas por dia", "Data",
				"N de vacinadas dadas");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		series.getData().addAll(new XYChart.Data<String, Number>("01/01", 10),
				new XYChart.Data<String, Number>("02/01", 20), new XYChart.Data<String, Number>("03/01", 100));

		series.setName("Casos novos por dia em " + city);

		chart.getData().add(series);

		return chart;
	}

}
