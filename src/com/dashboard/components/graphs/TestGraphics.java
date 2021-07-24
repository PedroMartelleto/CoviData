package com.dashboard.components.graphs;

import com.dashboard.data.importer.ChartsImporter;
import com.dashboard.data.model.MapChartDataModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestGraphics extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		
		ChartsImporter importer = new ChartsImporter();
		MapChartDataModel mapData =  importer.getDeathsMapChart();
		MapChart m = new MapChart(mapData.GetDots());
		
		
		BarChart<String, Number> l = BarChartsFactory.casesPerDay("Birigui");
		//LineChart<String, Number> l2 = CovidLineCharts.getLineChartNumberString("Teste 2", "X", "Y");
		
		StackPane root = new StackPane();
		root.getChildren().add(m.getMap());
		Scene scene = new Scene(root, 500, 500);
		
		window.setScene(scene);
		
		window.show();
	}

}
