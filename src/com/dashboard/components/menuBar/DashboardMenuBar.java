package com.dashboard.components.menuBar;

import com.dashboard.main.Scenes;
import com.dashboard.utils.FXMLUtils;

import javafx.beans.NamedArg;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Menu bar with buttons for navigation between scenes.
 * 
 * @author Pedro
 */
public class DashboardMenuBar extends HBox {
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	@FXML
	private Button vaccinationsButton;

	@FXML
	private Button infectedButton;

	@FXML
	private Button vaccineScheduleButton;
	
	@FXML
	private Button newsReportsButton;
	
	private String viewSelected;

	public DashboardMenuBar(@NamedArg("selected") String selected) {
		super();
		
		FXMLUtils.loadFXML(this);
		viewSelected = selected;
		
		if (viewSelected != null) {
			switch (viewSelected) {
			case "vaccinations":
				vaccinationsButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
				break;
			case "infected":
				infectedButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
				break;
			case "vaccineSchedule":
				vaccineScheduleButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
				break;
			case "news":
				newsReportsButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
				break;
			}
		} else {
			vaccinationsButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		}
	}

	/**
	 * Called during initialization.
	 */
	@FXML
	private void initialize() {
		vaccinationsButton.setOnMouseClicked(event -> {
			setWindowScene(Scenes.getVaccinations(), vaccinationsButton);
		});

		infectedButton.setOnMouseClicked(event -> {
			setWindowScene(Scenes.getInfected(), infectedButton);
		});

		vaccineScheduleButton.setOnMouseClicked(event -> {
			setWindowScene(Scenes.getVaccineSchedule(), vaccineScheduleButton);
		});
		
		newsReportsButton.setOnMouseClicked(event -> {
			setWindowScene(Scenes.getNewsReport(), newsReportsButton);
		});
	}

	/**
	 * Sets the currently displayed scene.
	 * 
	 * @param parent
	 */
	private void setWindowScene(Scene scene, Button button) {
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