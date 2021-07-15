package com.dashboard.data.source;

import java.util.Date;
import java.util.TreeMap;

/**
 * A generic data source used to populate histograms and line plots.
 * @author Pedro
 */
public interface TimeSeriesDataSource {
	/**
	 * Returns the color that should represent this time series.
	 * @return
	 */
	public String getTimeSeriesPlotColor();
	
	/**
	 * Returns the label that should represent this time series.
	 * @return
	 */
	public String getTimeSeriesPlotLabel();
	
	/**
	 * Returns the value of the time series.
	 * @return
	 */
	public TreeMap<Date, Double> getTimeSeries();
}
