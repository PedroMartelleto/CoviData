package com.dashboard.components.menuBar;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class CDMenuBar extends VBox {

    @FXML
    private TextArea editableText;
    @FXML
    private TextArea upperText;

    @FXML
    private void initialize() {
    	upperText.textProperty().bind(editableText.textProperty());
    }
	
    public CDMenuBar() {
        FXMLUtils.loadFXML(this);
    }
}