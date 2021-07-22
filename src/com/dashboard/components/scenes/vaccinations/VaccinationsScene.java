package com.dashboard.components.scenes.vaccinations;

import com.dashboard.components.animations.DisplayNumber;
import com.dashboard.components.animations.LabelAnimator;
import com.dashboard.components.graphs.TimeSeriesDataProvider;
import com.dashboard.data.common.BrazilStates;
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
	private ChoiceBox<String> stateChoiceBox;
	
	@FXML
	private AreaChart<String, Number> vaccinationsChart;
	
	@FXML
	private Label vaccinatedLast7Days;
	
	@FXML
	private Label twoDosesLabel;
	
	@FXML
	private Label oneDoseLabel;
	
	@FXML
	private Label monthsLeftLabel;
	
	@FXML
    private void initialize() {
		TimeSeriesDataProvider.vaccinations(vaccinationsChart);
		
		setupAnimations();
		setupChoiceBoxes();
	}
	
	private void setupAnimations() {
		LabelAnimator animator = new LabelAnimator();
		animator.addLabel(vaccinatedLast7Days, new DisplayNumber(3.4f, 1, "%"), (float)2e-7);
		animator.addLabel(twoDosesLabel, new DisplayNumber(5.2f, 1, "%"), (float)2e-7);
		animator.addLabel(oneDoseLabel, new DisplayNumber(5.2f, 1, "%"), (float)2e-7);
		animator.addLabel(monthsLeftLabel, new DisplayNumber(10, 1, " meses"), (float)2e-7);
		animator.start();
	}
	
	private void setupChoiceBoxes() {
		stateChoiceBox.getItems().add("Todos os estados");
		stateChoiceBox.getItems().addAll(BrazilStates.getStateNames());
		
		displayModeChoiceBox.getItems().add("Números relativos");
		displayModeChoiceBox.getItems().add("Números absolutos");

		// Default selection for choice boxes
		stateChoiceBox.getSelectionModel().selectFirst();
		displayModeChoiceBox.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void destroy() {
		
	}
	
    public VaccinationsScene() {
    	FXMLUtils.loadFXML(this);
    }
}
