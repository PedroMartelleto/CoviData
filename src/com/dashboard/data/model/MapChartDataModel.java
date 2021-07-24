package com.dashboard.data.model;

import java.util.ArrayList;
import javafx.util.Pair;
import com.sothawo.mapjfx.Coordinate;

public class MapChartDataModel {
	private ArrayList< Pair<Coordinate, Double> > pins = new ArrayList< Pair<Coordinate, Double> >();
	
	public final void addPin(double lat, double lon, double size) {
		this.pins.add(new Pair<>(new Coordinate(lat, lon), size));
	}
	
	public final ArrayList< Pair<Coordinate,Double> > GetDots() {
		return this.pins;
	}
}
