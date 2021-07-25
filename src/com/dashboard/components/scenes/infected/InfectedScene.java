package com.dashboard.components.scenes.infected;

import java.util.List;

import com.dashboard.components.animations.DisplayNumber;
import com.dashboard.components.animations.LabelAnimator;
import com.dashboard.components.graphs.MapDataProvider;
import com.dashboard.components.graphs.TimeSeriesDataProvider;
import com.dashboard.data.common.BrazilData;
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
	// MARK: FXML Components

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

	public InfectedScene() {
		FXMLUtils.loadFXML(this);
	}

	@FXML
	private void initialize() {
		stateChoiceBox.getItems().addAll(BrazilData.getStateNames());
		stateChoiceBox.getSelectionModel().selectFirst();

		animator.addLabel(totalCasesLabel, 0, "", (float) 2e-2);
		animator.addLabel(totalDeathsLabel, 0, "", (float) 2e-2);
		animator.start();

		stateChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
				return;
			}

			onStateNameChange(newValue);
		});

		casesMapCircles = MapDataProvider.casesByState();
		deathsMapCircles = MapDataProvider.deathsByState();
		initMap();

		onStateNameChange(stateChoiceBox.getSelectionModel().getSelectedItem());
	}

	private void onStateNameChange(String state) {
		animator.setLabelTarget(totalCasesLabel, new DisplayNumber(19524865, 0, ""));
		animator.setLabelTarget(totalDeathsLabel, new DisplayNumber(415203, 0, ""));

		TimeSeriesDataProvider.cases(casesChart, 5, state);
		TimeSeriesDataProvider.deaths(deathsChart, 5, state);
	}

	public void initMap() {
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
//		casesMapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).interactive(false).build());
//		deathsMapView
//				.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).interactive(false).build());
	}

	/**
	 * Helper function that initializes a map and adds a list of circles to it.
	 * 
	 * @param map
	 * @param circles
	 */
	private void addCirclesToMap(MapView map, List<MapCircle> circles) {
		map.setCenter(BrazilData.CENTER);
		map.setZoom(4.4);

		for (MapCircle circle : circles) {
			map.addMapCircle(circle);
		}
	}
}
