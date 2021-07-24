package com.dashboard.components.graphs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

import com.dashboard.data.common.BrazilData;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;

public class MapChart {
	
	private List<MapCircle> circles;
	
	public MapChart(ArrayList<Pair<Coordinate, Pair<String, Integer>>> dados) {
		this.circles = new ArrayList<MapCircle>();
		for(Pair<Coordinate, Pair<String, Integer>> entry : dados) {
			Coordinate coordinate = entry.getKey();
			String state = entry.getValue().getKey();
			int value = entry.getValue().getValue();
			System.out.println(state + ": " + value);
			
			double valueOfCircle = value*((double)1500000/BrazilData.getPopulation(state));
			MapCircle circle = new MapCircle(coordinate, valueOfCircle).setVisible(true);
			this.circles.add(circle);
		}
	}
	
	public Parent getMap() {
		String fxmlFile = "./resource/map.fxml";
		
		try {
			InputStream stream = getClass().getResourceAsStream(fxmlFile);
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			Parent node = fxmlLoader.load(stream);
			
			MapController controller = fxmlLoader.getController();
			
			controller.initMap();
			
			return node;
			
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

}
