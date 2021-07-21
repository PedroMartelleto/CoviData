package com.dashboard.components.graphs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MapChart {
	
	private List<MapCircle> circles;
	
	public MapChart(Map<Coordinate, Integer> dados) {
		this.circles = new ArrayList<MapCircle>();
		for(Map.Entry<Coordinate, Integer> entry : dados.entrySet()) {
			MapCircle circle = new MapCircle(entry.getKey(), entry.getValue()).setVisible(true);
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
			
			controller.initMap(this.circles);
			
			return node;
			
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

}
