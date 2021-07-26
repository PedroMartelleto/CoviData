package com.dashboard.components.scenes.infected;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.components.animations.DisplayNumber;
import com.dashboard.components.animations.LabelAnimator;
import com.dashboard.data.globals.BrazilGlobals;
import com.dashboard.data.importer.cssegiSandOwid.CSSEGISandOwidImporter;
import com.dashboard.data.providers.MapDataProvider;
import com.dashboard.data.providers.TimeSeriesDataProvider;
import com.dashboard.utils.FXMLUtils;
import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.MapCircle;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Scene showing the current state of the daily infected/deaths.
 */
public class InfectedScene extends AnchorPane {
	// FXML elements
	
	@FXML
	private MapView casesMapView;

	@FXML
	private MapView deathsMapView;

	@FXML
	private Label totalCasesLabel;

	@FXML
	private Label totalDeathsLabel;

	@FXML
	private ChoiceBox<String> stateChoiceBox;

	@FXML
	private AreaChart<String, Number> casesChart;

	@FXML
	private AreaChart<String, Number> deathsChart;

	/**
	 * Responsible for animating numeric transitions in labels.
	 */
	private LabelAnimator animator = new LabelAnimator();

	/**
	 * List of circles displayed on the cases map.
	 */
	private List<MapCircle> casesMapCircles;

	/**
	 * List of circles displayed on the deaths map.
	 */
	private List<MapCircle> deathsMapCircles;
	
	/**
	 * Provides the data for the charts.
	 */
	private TimeSeriesDataProvider timeSeriesDataProvider;
	
	/**
	 * Provides the data for the maps.
	 */
	private MapDataProvider mapDataProvider;
	
	/**
	 * Creates a new instance of InfectedScene.
	 */
	public InfectedScene() {
		FXMLUtils.loadFXML(this);
	}

	@FXML
	private void initialize() {
		// Inits the providers w/ the importers
		timeSeriesDataProvider = new TimeSeriesDataProvider(new CSSEGISandOwidImporter());
		mapDataProvider = new MapDataProvider(new CSSEGISandOwidImporter());
		
		// Inits choice boxes and animations
		stateChoiceBox.getItems().addAll(BrazilGlobals.getStateNames());
		stateChoiceBox.getSelectionModel().selectFirst();

		animator.addLabel(totalCasesLabel, 0, "", (float) 2e-2);
		animator.addLabel(totalDeathsLabel, 0, "", (float) 2e-2);
		animator.start();
		
		// When the state changes, updates the charts and constants displayed
		stateChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
				return;
			}
			casesChart.getData().remove(0);
			deathsChart.getData().remove(0);

			onStateNameChange(newValue);
		});
		
		// Inits JFX maps
		initMap();
		
		// Updates the UI once.
		onStateNameChange(stateChoiceBox.getSelectionModel().getSelectedItem());
	}

	/**
	 * Called whenever the state name choice box changes.
	 * @param state
	 */
	private void onStateNameChange(String state) {
		timeSeriesDataProvider.cases(casesChart, 5, state);
		timeSeriesDataProvider.deaths(deathsChart, 5, state);
		
		animator.setLabelTarget(totalCasesLabel, new DisplayNumber(timeSeriesDataProvider.totalCases, 0, ""));
		animator.setLabelTarget(totalDeathsLabel, new DisplayNumber(timeSeriesDataProvider.totalDeaths, 0, ""));
	}

	private void initMap() {
		casesMapCircles = mapDataProvider.casesByState();
		deathsMapCircles = mapDataProvider.deathsByState();
		
		// Adds events to run after the maps have been initialized
		casesMapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue)
				addCirclesToMap(casesMapView, casesMapCircles);
		});

		deathsMapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue)
				addCirclesToMap(deathsMapView, deathsMapCircles);
		});

		// Initializes the map
		casesMapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).interactive(false).build());
		deathsMapView
				.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).interactive(false).build());
	}

	/**
	 * Helper function that initializes a map and adds a list of circles to it.
	 * 
	 * @param map
	 * @param circles
	 */
	private void addCirclesToMap(MapView map, List<MapCircle> circles) {
		map.setCenter(BrazilGlobals.CENTER);
		map.setZoom(4.4);

		for (MapCircle circle : circles) {
			map.addMapCircle(circle);
		}
	}
}
