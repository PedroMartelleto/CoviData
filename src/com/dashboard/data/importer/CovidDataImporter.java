package com.dashboard.data.importer;

import com.dashboard.data.model.XYChartDataModel;
import com.dashboard.data.model.MapDataModel;

/**
 * Interface that represents a data origin. To get data from another API, implement this interface.
 */
public interface CovidDataImporter {
	
	public XYChartDataModel[] getVaccinationsLineChart();	
	
	public XYChartDataModel getDailyCasesLineChart(String stateName);
	
	public XYChartDataModel getDailyDeathsLineChart(String stateName);
	
	public MapDataModel getCasesMapChart();

	public MapDataModel getDeathsMapChart();
}
