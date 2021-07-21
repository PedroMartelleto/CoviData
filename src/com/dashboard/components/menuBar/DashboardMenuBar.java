package com.dashboard.components.menuBar;

import com.dashboard.components.scenes.hospitals.HospitalsScene;
import com.dashboard.components.scenes.infected.InfectedScene;
import com.dashboard.components.scenes.newsReports.NewsReportsScene;
import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.components.scenes.vaccineSchedule.VaccineScheduleScene;
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
    private Button hospitalsButton;
    
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
    		setWindowScene(new VaccinationsScene());
    	});
    	
    	infectedButton.setOnMouseClicked(event -> {
    		setWindowScene(new InfectedScene());
    	});
    	
    	hospitalsButton.setOnMouseClicked(event -> {
    		setWindowScene(new HospitalsScene());
    	});
    	
    	newsButton.setOnMouseClicked(event -> {
    		setWindowScene(new NewsReportsScene());
    	});
    	
    	vaccineScheduleButton.setOnMouseClicked(event -> {
    		setWindowScene(new VaccineScheduleScene());
    	});
    }
	
    public DashboardMenuBar() {
    	FXMLUtils.loadFXML(this);
    }
    
    /**
     * Sets the currently displayed scene.
     * @param parent
     */
    private void setWindowScene(Parent parent) {
    	Stage window = (Stage) getScene().getWindow();
    	
    	// Checks if we actually need to change scenes
    	String currentClassName = window.getScene().getRoot().getClass().getName();
    	String newClassName = parent.getClass().getName();

    	if (currentClassName.equals(newClassName)) {
    		// Nothing to do!
    		return;
    	}
    	
		CSSUtils.addStylesheetsToParent(parent);
		window.setScene(new Scene(parent));
    }
}