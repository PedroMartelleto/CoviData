package com.dashboard.data.interfaces;

import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;

public interface ChartsInterface {
	
	// Vacinacao por data no pais
	public LineChartDataModel[] getVaccinationsLineChart();	
	
	// Casos diarios no Pais
	public LineChartDataModel getDailyTotalCasesLineChart(String stateName);

	// Casos diarios nos estados
	public LineChartDataModel[] getDailyStateCasesLineChart(String stateName);
	
	// Casos diarios no Pais
	public LineChartDataModel getDailyTotalDeathsLineChart(String stateName);

	// Casos diarios nos estados
	public LineChartDataModel[] getDailyStateDeathsLineChart(String stateName);
	
	// Casos totais por estado
	public MapChartDataModel getCasesMapChart();

	// Mortes totais por estado
	public MapChartDataModel getDeathsMapChart();
}
