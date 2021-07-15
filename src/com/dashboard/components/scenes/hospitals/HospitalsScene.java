package com.dashboard.components.scenes.hospitals;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Scene showing the current state of the hospitals regarding covid.
 * @author Pedro
 */
public class HospitalsScene extends AnchorPane {
	@FXML
    private void initialize() {
    }
	
    public HospitalsScene() {
    	FXMLUtils.loadFXML(this);
    }
}

