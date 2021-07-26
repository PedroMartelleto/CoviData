package com.dashboard.data.model;

import java.util.ArrayList;
import javafx.util.Pair;
import com.sothawo.mapjfx.Coordinate;

/**
 * Data model for maps.
 */
public class MapDataModel {
	private ArrayList< Pair<Coordinate, Double> > pins = new ArrayList< Pair<Coordinate, Double> >();
	
	/**
	 * Adds a new pin to this data model.
	 * @param lat latitude of the pin.
	 * @param lon longitude of the pin.
	 * @param size size of the pin.
	 */
	public final void addPin(double lat, double lon, double size) {
		this.pins.add(new Pair<>(new Coordinate(lat, lon), size));
	}
	
	/**
	 * Gets a list of the added pins.
	 * @return
	 */
	public final ArrayList< Pair<Coordinate,Double> > getPins() {
		return this.pins;
	}
}
