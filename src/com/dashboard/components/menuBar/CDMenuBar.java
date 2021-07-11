package com.dashboard.components.menuBar;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

// TODO: Descobrir como fazer isso funcionar https://blog.zelinf.net/posts/java/2017-12-19-javafx-custom-component.html
public class CDMenuBar extends HBox {
	
    public CDMenuBar() {
        FXMLUtils.loadFXML(this);
    }

    @FXML
    private void initialize() {
    }
}