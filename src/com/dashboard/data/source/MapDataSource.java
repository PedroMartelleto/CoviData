package com.dashboard.data.source;

import java.util.Dictionary;

/**
 * A generic data source used to populate maps.
 * @author Pedro
 */
public interface MapDataSource<T> {
	/**
	 * Returns a dictionary that maps the name of some location to its color.
	 * @return
	 */
	public Dictionary<String, String> getMapColors();
	
	/**
	 * Returns a dictionary that maps the name of some location to its label.
	 * @return
	 */
	public Dictionary<String, String> getMapLabels();
	
	/**
	 * Returns a dictionary that maps the name of some location to its value.
	 * @return
	 */
	public Dictionary<String, T> getMapValues();
}
