package com.dashboard.components.scenes.infected;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Scene showing the current state of the daily infected/deaths.
 * @author Pedro
 */
public class InfectedScene extends AnchorPane {
	@FXML
    private void initialize() {
    }
	
    public InfectedScene() {
    	FXMLUtils.loadFXML(this);
    }
}
