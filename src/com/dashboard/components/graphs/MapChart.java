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
	public MapChart() {
		
	}
	
	public  Parent getMap() {
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
