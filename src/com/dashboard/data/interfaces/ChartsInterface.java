package com.dashboard.data.interfaces;

import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;

public interface ChartsInterface {
	public LineChartDataModel[] getVaccinationsLineChart();	
	

	public LineChartDataModel getDailyCasesLineChart(String stateName);
	
	public LineChartDataModel getDailyDeathsLineChart(String stateName);
	
	public MapChartDataModel getCasesMapChart();

	public MapChartDataModel getDeathsMapChart();
}
