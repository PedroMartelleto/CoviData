package com.dashboard.components.graphs;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.data.common.BrazilStates;
import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class MapController {
	
	@FXML
	private MapView mapView;
		
	private Coordinate center;
	
	private int defaultZoom = 4;
	
	private List<MapCircle> circles = new ArrayList<MapCircle>();
	
	public MapController() {
		this.center = new Coordinate(-15.723588679352947, -46.98361582418985);
	}
	
	
	public void initMap() {
		Projection projection = Projection.WEB_MERCATOR;
		
		// watch the MapView's initialized property to finish initialization
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterInit();
            }
        });
		
		mapView.initialize(Configuration.builder()
				.projection(projection)
				.interactive(false)
				.build()
				);
		
	}
	
	private void afterInit() {
		System.out.println("Após inicialização");
		mapView.setCenter(center);
		mapView.setZoom(defaultZoom);
		
		for(MapCircle circle : this.circles) {
			mapView.addMapCircle(circle);
		}
	}
}
