package com.dashboard.data.interfaces;

import java.util.Map;

import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;

public interface ChartsInterface {
	
	public LineChartDataModel[] getVaccinationsLineChart();	
	
	public LineChartDataModel getDailyTotalCasesLineChart(String stateName);

	public LineChartDataModel getDailyStateCasesLineChart(String stateName);
	
	public LineChartDataModel getDailyTotalDeathsLineChart(String stateName);

	public LineChartDataModel getDailyStateDeathsLineChart(String stateName);
	
	public MapChartDataModel getCasesMapChart();

	public MapChartDataModel getDeathsMapChart();
}
