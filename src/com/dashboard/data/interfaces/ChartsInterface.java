package com.dashboard.data.interfaces;

import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;

public interface ChartsInterface {
	
	// Casos totais por estado
	public MapChartDataModel GetCasesMapChart();

	// Mortes totais por estado
	public MapChartDataModel GetDeathsMapChart();
	
	// Vacinação por data no país
	public LineChartDataModel GetVaccinationsLineChart();
	
	// Casos diários no País
	public LineChartDataModel GetDailyTotalCasesLineChart();

	// Casos diários nos estados
	public LineChartDataModel[] GetDailyStateCasesLineChart();
	
	// Casos diários no País
	public LineChartDataModel GetDailyTotalDeathsLineChart();

	// Casos diários nos estados
	public LineChartDataModel[] GetDailyStateDeathsLineChart();
}
