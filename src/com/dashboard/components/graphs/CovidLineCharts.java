package com.dashboard.components.graphs;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class CovidLineCharts {
	
	private static LineChart<String, Number> getLineChart(String title, String xLabel, String yLabel){
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		
		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		
		lineChart.setTitle(title);
		
		return lineChart;
	}
	
	public static LineChart<String, Number> getVacinnePerDateCountry(String startDate, String endDate){
		LineChart <String, Number> lineChart = getLineChart("Vacinacão por covid diária no país", "Data", "Número de Vacinados");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
		
		series.getData().addAll(new XYChart.Data<String,Number>("01/01", 10),
								new XYChart.Data<String,Number>("02/01", 20),
								new XYChart.Data<String,Number>("03/01", 100));
		
		series.setName("Vacinação por dia Brasil");
		
		lineChart.getData().add(series);
		
		return lineChart;
	}
	
	public static LineChart<String, Number> getVacinnePerDateCity(String city, String startDate, String endDate){
		LineChart <String, Number> lineChart = getLineChart("Vacinacão por covid diária em" +city, "Data", "Número de Vacinados");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
		
		series.getData().addAll(new XYChart.Data<String,Number>("01/01", 10),
								new XYChart.Data<String,Number>("02/01", 20),
								new XYChart.Data<String,Number>("03/01", 100));
		
		series.setName("Vacinação por dia " + city);
		
		lineChart.getData().add(series);
		
		return lineChart;
	}
	
	public static LineChart<String, Number> getCovidCasesPerDay(){
		LineChart <String, Number> l = getLineChart("Casos acumulados de COVID-19 por data de\n"
				+ "notificação", "Data de Notificação", "Casos Acumulados");
		
		String[] estados = new String[] {"Sp", "Rj", "Ce"};
		
		for(int i = 0; i < estados.length; i++) {
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			
			series.getData().addAll(new XYChart.Data<String,Number>("01/01", 10),
					new XYChart.Data<String,Number>("02/01", 20),
					new XYChart.Data<String,Number>("03/01", 100));
			
			series.setName(estados[i]);
			
			l.getData().add(series);
		}
		
		return l;
	}

	public static LineChart<String, Number> getCovidDeathsPerDay(){
		LineChart <String, Number> l = getLineChart("Óbitos acumulados de COVID-19 por data de\n"
				+ "notificação", "Data de Notificação", "Casos Acumulados");
		
		String[] estados = new String[] {"Sp", "Rj", "Ce"};
		
		for(int i = 0; i < estados.length; i++) {
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			
			series.getData().addAll(new XYChart.Data<String,Number>("01/01", 10),
					new XYChart.Data<String,Number>("02/01", 20),
					new XYChart.Data<String,Number>("03/01", 100));
			
			series.setName(estados[i]);
			
			l.getData().add(series);
		}
		
		return l;
	}

}
