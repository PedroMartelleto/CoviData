package com.dashboard.components.menuBar;

import com.dashboard.components.scenes.infected.InfectedScene;
import com.dashboard.components.scenes.newsReports.NewsReportsScene;
import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.components.scenes.vaccineSchedule.VaccineScheduleScene;
import com.dashboard.main.Scenes;
import com.dashboard.utils.CSSUtils;
import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Menu bar with buttons for navigation between scenes.
 * @author Pedro
 */
public class DashboardMenuBar extends HBox {

    @FXML
    private Button vaccinationsButton;
    
    @FXML
    private Button infectedButton;

    @FXML
    private Button newsButton;
    
    @FXML
    private Button vaccineScheduleButton;
    
    /**
     * Called during initialization.
     */
    @FXML
    private void initialize() {
    	vaccinationsButton.setOnMouseClicked(event -> {
    		setWindowScene(Scenes.getVaccinations());
    	});
    	
    	infectedButton.setOnMouseClicked(event -> {
    		setWindowScene(Scenes.getInfected());
    	});
    	
    	newsButton.setOnMouseClicked(event -> {
    		setWindowScene(Scenes.getNewsReport());
    	});
    	
    	vaccineScheduleButton.setOnMouseClicked(event -> {
    		setWindowScene(Scenes.getVaccineSchedule());
    	});
    }
	
    public DashboardMenuBar() {
    	FXMLUtils.loadFXML(this);
    }
    
    /**
     * Sets the currently displayed scene.
     * @param parent
     */
    private void setWindowScene(Scene scene) {
    	Stage window = (Stage) getScene().getWindow();
    	
    	// Checks if we actually need to change scenes
    	String currentClassName = window.getScene().getRoot().getClass().getName();
    	String newClassName = scene.getRoot().getClass().getName();

    	if (currentClassName.equals(newClassName)) {
    		// Nothing to do!
    		return;
    	}
    	
		window.setScene(scene);
    }
}