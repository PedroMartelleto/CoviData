package com.dashboard.components.graphs;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarChartsFactory {
	private static BarChart<String, Number> barChart(String title, String xLabel, String yLabel) {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		
		barChart.setTitle(title);
		
		return barChart;
	}
	
	
	public static BarChart<String, Number> casesPerDay(String city) {
		BarChart<String, Number> barChart = barChart("Casos novos de COVID-19 por data de"
			+	"notificacao", "Data de Notificacao", "Casos novos");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		series.getData().addAll(new XYChart.Data<String,Number>("01/01", 10),
				new XYChart.Data<String,Number>("02/01", 20),
				new XYChart.Data<String,Number>("03/01", 100));
		
		series.setName("Casos novos por dia em " + city);
		
		barChart.getData().add(series);
		
		return barChart;
	}
	
	public static BarChart<String, Number> deathsPerDay(String city) {
		BarChart<String, Number> barChart = barChart("Óbitos de COVID-19 por data de notificação", "Data de Notificação", "Óbitos");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		series.getData().addAll(new XYChart.Data<String,Number>("01/01", 10),
				new XYChart.Data<String,Number>("02/01", 20),
				new XYChart.Data<String,Number>("03/01", 100));
		
		series.setName("Óbitos por dia em " + city);
		
		barChart.getData().add(series);
		
		return barChart;
	}

}
