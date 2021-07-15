package com.dashboard.components.scenes.vaccinations;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Status of covid vaccinations.
 * @author Pedro
 */
public class VaccinationsScene extends AnchorPane {
	@FXML
    private void initialize() {
    }
	
    public VaccinationsScene() {
    	FXMLUtils.loadFXML(this);
    }
}
