package com.dashboard.data.interfaces;

import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;

public interface ChartsInterface {
	
	// Casos totais por estado
	public MapChartDataModel GetCasesMapChart();

	// Mortes totais por estado
	public MapChartDataModel GetDeathsMapChart();
	
	// Vacina��o por data no pa�s
	public LineChartDataModel GetVaccinationsLineChart();
	
	// Casos di�rios no Pa�s
	public LineChartDataModel GetDailyTotalCasesLineChart();

	// Casos di�rios nos estados
	public LineChartDataModel[] GetDailyStateCasesLineChart();
	
	// Casos di�rios no Pa�s
	public LineChartDataModel GetDailyTotalDeathsLineChart();

	// Casos di�rios nos estados
	public LineChartDataModel[] GetDailyStateDeathsLineChart();
}
