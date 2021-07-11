package com.dashboard.components.graphs;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import com.sothawo.mapjfx.*;

public class TestGraphics extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		MapView mapView = new MapView(); 
		mapView.initialize();
		BarChart<String, Number> l = CovidBarCharts.getNewCasesPerDate("Birigui");
		//LineChart<String, Number> l2 = CovidLineCharts.getLineChartNumberString("Teste 2", "X", "Y");
		
		StackPane root = new StackPane();
		root.getChildren().add(l);
		Scene scene = new Scene(root, 500, 500);
		
		window.setScene(scene);
		
		window.show();
	}

}
