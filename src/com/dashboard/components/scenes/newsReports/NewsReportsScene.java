package com.dashboard.components.scenes.newsReports;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Covid-related news.
 * @author Pedro
 */
public class NewsReportsScene extends AnchorPane {
	@FXML
    private void initialize() {
    }
	
    public NewsReportsScene() {
    	FXMLUtils.loadFXML(this);
    }
}
