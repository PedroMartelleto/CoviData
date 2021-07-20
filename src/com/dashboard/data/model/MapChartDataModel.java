package com.dashboard.data.model;

import java.util.ArrayList;
import javafx.util.Pair;
import com.sothawo.mapjfx.Coordinate;

public class MapChartDataModel {
	private ArrayList< Pair<Coordinate, Integer> > pins = new ArrayList< Pair<Coordinate, Integer> >();
	
	public final void AddPin(double lat, double lon, int size) {
		this.pins.add(new Pair<>(new Coordinate(lat, lon), size));
	}
	
	public final ArrayList< Pair<Coordinate, Integer> > GetDots() {
		return this.pins;
	}
}
