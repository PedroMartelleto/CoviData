package com.dashboard.components.scenes.vaccinations;

import com.dashboard.components.animations.DisplayNumber;
import com.dashboard.components.animations.LabelAnimator;
import com.dashboard.components.graphs.TimeSeriesDataProvider;
import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Status of covid vaccinations.
 */
public class VaccinationsScene extends AnchorPane {	
	@FXML
	private ChoiceBox<String> displayModeChoiceBox;
	
	@FXML
	private AreaChart<String, Number> vaccinationsChart;
	
	@FXML
	private Label titleVaccination;
	
	@FXML
	private Label vaccinatedLast7Days;
	
	@FXML
	private Label twoDosesLabel;
	
	@FXML
	private Label oneDoseLabel;
	
	@FXML
	private Label monthsLeftLabel;
	
	private LabelAnimator animator = new LabelAnimator();
	
	@FXML
    private void initialize() {		
		setupAnimations();
		setupChoiceBoxes();
	}
	
	private void updateChartData(boolean relative) {
		String displayMode = displayModeChoiceBox.getSelectionModel().getSelectedItem();
		vaccinationsChart.getData().clear();
		TimeSeriesDataProvider.vaccinations(vaccinationsChart, 5, relative);
	}
	
	private void setupAnimations() {
		animator.addLabel(vaccinatedLast7Days, 1, "%", (float)2e-7);
		animator.addLabel(twoDosesLabel, 1, "%", (float)2e-7);
		animator.addLabel(oneDoseLabel, 1, "%", (float)2e-7);
		animator.addLabel(monthsLeftLabel, 1, "meses", (float)2e-7);
		animator.setLabelTarget(monthsLeftLabel, new DisplayNumber(4.3f, 1, " meses"));
		animator.start();
		
		setLabelsTargets(true);
	}
	
	private void setLabelsTargets(boolean relativeNumbers) {
		if (relativeNumbers) {
			animator.setLabelTarget(vaccinatedLast7Days, new DisplayNumber(3.4f, 1, "%"));
			animator.setLabelTarget(twoDosesLabel, new DisplayNumber(20.1f, 0, "%"));
			animator.setLabelTarget(oneDoseLabel, new DisplayNumber(33.2f, 0, "%"));
		} else {
			animator.setLabelTarget(vaccinatedLast7Days, new DisplayNumber(40, 0, " m"));
			animator.setLabelTarget(twoDosesLabel, new DisplayNumber(50, 0, " m"));
			animator.setLabelTarget(oneDoseLabel, new DisplayNumber(30, 0, " m"));
		}
	}
	
	private void setupChoiceBoxes() {	
		displayModeChoiceBox.getItems().add("Números relativos");
		displayModeChoiceBox.getItems().add("Números absolutos");
		
		// Relative/absolute numbers chooser event
		displayModeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
			(observable, oldValue, newValue) -> {
				if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
					return;
				}
				
				setLabelsTargets(newValue.equals("Números relativos"));
				updateChartData(newValue.equals("Números relativos"));
			}
		);
		
		// Default selection for choice boxes
		displayModeChoiceBox.getSelectionModel().selectFirst();
		updateChartData(true);
	}
	
    public VaccinationsScene() {
    	FXMLUtils.loadFXML(this);
    }
}
