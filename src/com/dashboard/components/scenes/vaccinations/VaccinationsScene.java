package com.dashboard.components.scenes.vaccinations;

import java.util.List;
import java.util.Observable;
import java.lang.Number;

import com.dashboard.components.animations.DisplayNumber;
import com.dashboard.components.animations.LabelAnimator;
import com.dashboard.data.globals.BrazilGlobals;
import com.dashboard.data.importer.cssegiSandOwid.CSSEGISandOwidImporter;
import com.dashboard.data.model.XYChartDataModel;
import com.dashboard.data.providers.TimeSeriesDataProvider;
import com.dashboard.utils.FXMLUtils;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
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
	
	private int vaccinatedLast7DaysNumber = 0;
	private int oneDosesNumber = 0;
	private int twoDosesNumber = 0;
	private float monthsTo70percent = 0.0f;
	
	private TimeSeriesDataProvider dataProvider;
	
	@FXML
    private void initialize() {
		dataProvider = new TimeSeriesDataProvider(new CSSEGISandOwidImporter());
		
		setupAnimations();
		setupChoiceBoxes();
	}
	
	/**
	 * Called whenever the choice box changes.
	 * @param relative
	 */
	private void onRelativeNumbersChoiceBoxChange(boolean relative) {
		vaccinationsChart.getData().clear();
		dataProvider.vaccinations(vaccinationsChart, 5, relative);
		
		if (this.vaccinatedLast7DaysNumber == 0) {
			this.vaccinatedLast7DaysNumber = dataProvider.vaccinatedLast7DaysNumber;
			this.oneDosesNumber = dataProvider.oneDosesNumber;
			this.twoDosesNumber = dataProvider.twoDosesNumber;
			this.monthsTo70percent = dataProvider.monthsTo70percent;
			
			setLabelsTargets(true);
		}
	}
	
	/**
	 * Updates the values of the labels.
	 * @param relativeNumbers
	 */
	private void setLabelsTargets(boolean relativeNumbers) {
		String suffix = relativeNumbers ? "%" : " M";
		float normFactor = relativeNumbers ? 0.01f * BrazilGlobals.getBrazilPopulation(): (float) 1e6 ;
				        
		animator.setLabelTarget(vaccinatedLast7Days, new DisplayNumber(Float.valueOf(this.vaccinatedLast7DaysNumber) / normFactor, 1, suffix));
		animator.setLabelTarget(twoDosesLabel, new DisplayNumber(Float.valueOf(this.twoDosesNumber) / normFactor, 1, suffix));
		animator.setLabelTarget(oneDoseLabel, new DisplayNumber(Float.valueOf(this.oneDosesNumber) / normFactor, 1, suffix));
		animator.setLabelTarget(monthsLeftLabel, new DisplayNumber(this.monthsTo70percent, 1, " meses"));
	}
	
	/**
	 * Called during initialization to setup label animations.
	 */
	private void setupAnimations() {
		animator.addLabel(vaccinatedLast7Days, 1, "%", (float)2e-2);
		animator.addLabel(twoDosesLabel, 1, "%", (float)2e-2);
		animator.addLabel(oneDoseLabel, 1, "%", (float)2e-2);
		animator.addLabel(monthsLeftLabel, 1, "meses", (float)2e-2);

		animator.start();
		
		setLabelsTargets(true);
	}
	
	/**
	 * Called during init to setup choice boxes.
	 */
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
				onRelativeNumbersChoiceBoxChange(newValue.equals("Números relativos"));
			}
		);
		
		// Default selection for choice boxes
		displayModeChoiceBox.getSelectionModel().selectFirst();
		onRelativeNumbersChoiceBoxChange(true);
	}
	
    public VaccinationsScene() {
    	FXMLUtils.loadFXML(this);
    }
}
