package com.dashboard.components.graphs;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sothawo.mapjfx.*;

public class TestGraphics extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		Map<Coordinate, Integer> mapa = new HashMap<Coordinate, Integer>();
		mapa.put(new Coordinate(-21.98761654615617, -48.783849369628065), 500000);
		MapChart m = new MapChart(mapa);
		
		
		BarChart<String, Number> l = CovidBarCharts.getNewCasesPerDate("Birigui");
		//LineChart<String, Number> l2 = CovidLineCharts.getLineChartNumberString("Teste 2", "X", "Y");
		
		StackPane root = new StackPane();
		root.getChildren().add(m.getMap());
		Scene scene = new Scene(root, 500, 500);
		
		window.setScene(scene);
		
		window.show();
	}

}
