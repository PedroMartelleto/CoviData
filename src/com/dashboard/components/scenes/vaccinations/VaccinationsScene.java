package com.dashboard.components.scenes.vaccinations;

import com.dashboard.components.graphs.TimeSeriesDataProvider;
import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.layout.AnchorPane;

/**
 * Status of covid vaccinations.
 */
public class VaccinationsScene extends AnchorPane {
	@FXML
	private AreaChart<String, Number> vaccinationsChart;
	
	@FXML
    private void initialize() {
		// System.out.println(vaccinationsChart.);
		TimeSeriesDataProvider.vaccinations(vaccinationsChart);
		// vaccinationsChart = AreaChartsFactory.vaccinationsPerDay("RJ");
    }
	
    public VaccinationsScene() {
    	FXMLUtils.loadFXML(this);
    }
}
