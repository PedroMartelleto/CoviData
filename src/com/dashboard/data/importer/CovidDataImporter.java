package com.dashboard.data.importer;

import com.dashboard.data.model.XYChartDataModel;
import com.dashboard.data.model.MapDataModel;

/**
 * Interface that represents a data origin. To get data from another API, implement this interface.
 */
public interface CovidDataImporter {
	/**
	 * Get data the a vaccinations chart.
	 * @return
	 */
	public XYChartDataModel[] getVaccinationsLineChart();	
	
	/**
	 * Gets daily cases data for a chart.
	 * @param stateName name of the state.
	 * @return
	 */
	public XYChartDataModel getDailyCasesLineChart(String stateName);
	
	/**
	 * Gets daily deaths data for a chart.
	 * @param stateName name of the state.
	 * @return
	 */
	public XYChartDataModel getDailyDeathsLineChart(String stateName);
	
	/**
	 * Gets the pin data for the cases map.
	 * @return
	 */
	public MapDataModel getCasesMapChart();

	/**
	 * Gets the pin data for the deaths map.
	 * @return
	 */
	public MapDataModel getDeathsMapChart();
}
