package com.dashboard.components.graphs;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.data.common.BrazilData;
import com.dashboard.data.importer.CSSEGISandOwidImporter;
import com.dashboard.data.model.MapChartDataModel;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;
import com.sothawo.mapjfx.MapView;

import javafx.scene.paint.Color;
import javafx.util.Pair;

public class MapDataProvider {
	private static MapChartDataModel data;
	private static CSSEGISandOwidImporter importer = new CSSEGISandOwidImporter();
	
	public static List<MapCircle> deathsByState() {
		data = importer.getDeathsMapChart();
		
		return getCircles(data.GetDots());
	}
	
	public static List<MapCircle> casesByState() {
		data = importer.getCasesMapChart();
		
		return getCircles(data.GetDots());
	}
	
	private static List<MapCircle> getCircles(List< Pair<Coordinate, Double>> data) {
		List<MapCircle> circles = new ArrayList<MapCircle>();
		
		
		for(Pair<Coordinate, Double> entry : data) {
			Coordinate coordinate = entry.getKey();
			double value = entry.getValue();
			
			Pair<Double, Color> circleSettings= getCircleSettings(value);
			
			circles.add(new MapCircle(coordinate, circleSettings.getKey())
					.setFillColor(circleSettings.getValue())
					.setColor(circleSettings.getValue())
					.setVisible(true));
		}
		return circles;
	}
	
	private static Pair<Double, Color> getCircleSettings(double value){
		double circleSize = 0;
		Color c = Color.WHITE;
		
		if(value >= 0 && value <= 0.8){
			double normalValue = (value) / 0.8 + 0.05;
			c = new Color(0, 0, 1, 0.7);
			
			circleSize = normalValue * 150000;
		}
		
		else if(value > 0.8 && value <= 1.2) {
			double normalValue = (value - 0.8) / 0.4 + 0.05;
			c = new Color(1, 1, 0, 0.7);
			
			circleSize = normalValue * 150000;
		}
		else {
			double normalValue = (value - 1.2) / 0.8 + 0.05;
			c = new Color(1, 0, 0, 0.7);
			
			circleSize = normalValue * 150000;
		}
		
		//System.out.println("Tamanho: " + circleSize + " Valor: " + value + " Estado: ");

		return new Pair<Double, Color>(circleSize, c);
	}
	

}
