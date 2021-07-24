package com.dashboard.data.model;

import java.util.ArrayList;
import javafx.util.Pair;
import com.sothawo.mapjfx.Coordinate;

public class MapChartDataModel {
	private ArrayList< Pair<Coordinate, Pair<String, Integer>> > pins = new ArrayList< Pair<Coordinate, Pair<String, Integer>> >();
	
	public final void addPin(double lat, double lon, int size, String state) {
		this.pins.add(new Pair<>(new Coordinate(lat, lon), new Pair<>(state, size)));
	}
	
	public final ArrayList< Pair<Coordinate, Pair<String, Integer>> > GetDots() {
		return this.pins;
	}
}
