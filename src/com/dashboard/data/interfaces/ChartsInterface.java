package com.dashboard.data.interfaces;

import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;

public interface ChartsInterface {
	
	// Casos totais por estado
	// Rambo
	public MapChartDataModel GetCasesMapChart();

	// Mortes totais por estado
	// Rambo
	public MapChartDataModel GetDeathsMapChart();
	
	// Vacinacao por data no pais
	public LineChartDataModel GetVaccinationsLineChart();	
	
	// Casos diarios no Pais
	public LineChartDataModel GetDailyTotalCasesLineChart();

	// Casos diarios nos estados
	public LineChartDataModel[] GetDailyStateCasesLineChart();
	
	// Casos diarios no Pais
	public LineChartDataModel GetDailyTotalDeathsLineChart();

	// Casos diarios nos estados
	public LineChartDataModel[] GetDailyStateDeathsLineChart();
}
