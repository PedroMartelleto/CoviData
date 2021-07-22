package com.dashboard.components.graphs;

import java.util.List;

import com.dashboard.data.common.PopulationByState;
import com.dashboard.data.importer.ChartsImporter;
import com.dashboard.data.model.MapChartDataModel;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;
import com.sothawo.mapjfx.MapView;

import javafx.scene.paint.Color;
import javafx.util.Pair;



public class MapDataProvider {
	private static MapChartDataModel data;
	private static ChartsImporter importer = new ChartsImporter();
	
	public static void deathsByState(MapView map) {
		data = importer.GetDeathsMapChart();
		
		InsertData(map, data.GetDots(), 2);
	}
	public static void casesByState(MapView map) {
		data = importer.GetDeathsMapChart();
		
		InsertData(map, data.GetDots(), 1);
	}
	
	private static void InsertData(MapView map, List< Pair<Coordinate, Pair<String, Integer>>> data, int type) {
		for(Pair<Coordinate, Pair<String, Integer>> entry : data) {
			Coordinate coordinate = entry.getKey();
			String state = entry.getValue().getKey();
			int value = entry.getValue().getValue();
			
			Pair<Double, Color> circleSettings= getCircleSettings(value, state, type);
			MapCircle circle = new MapCircle(coordinate, circleSettings.getKey())
					.setColor(circleSettings.getValue())
					.setVisible(true);
			map.addMapCircle(circle);
		}
	}
	
	private static Pair<Double, Color> getCircleSettings(int value, String state, int type){
		double circleSize = 0;
		Color c = Color.WHITE;
		if(type == 1) { //map of cases
			circleSize = value*((double)1500000/PopulationByState.getPopulation(state));
			
			if(value > 1500000) {
				c = Color.RED;
			}
			else if (value > 1000000) {
				c = Color.ORANGE;
			}
			else if (value > 500000) {
				c = Color.YELLOW;
			}
			else if(value > 250000) {
				c = Color.GREENYELLOW;
			}
			else {
				c = Color.LAWNGREEN;
			}
		}
		else if (type == 2) { //map of deaths
			circleSize = value*((double)150000000/PopulationByState.getPopulation(state));
			if(value > 60000) {
				c = Color.RED;
			}
			else if (value > 40000) {
				c = Color.ORANGE;
			}
			else if (value > 200000) {
				c = Color.YELLOW;
			}
			else if(value > 10000) {
				c = Color.GREENYELLOW;
			}
			else {
				c = Color.LAWNGREEN;
			}
		}
		
		return new Pair<Double, Color>(circleSize, c);
	}
	

}
