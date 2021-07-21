package com.dashboard.components.scenes.vaccineSchedule;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Covid-related news.
 */
public class VaccineScheduleScene extends AnchorPane {
	@FXML
    private void initialize() {
    }
	
    public VaccineScheduleScene() {
    	FXMLUtils.loadFXML(this);
    }
}
