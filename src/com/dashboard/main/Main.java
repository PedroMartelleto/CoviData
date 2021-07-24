package com.dashboard.main;

import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.utils.CSSUtils;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: Vaccines page
	// TODO: Overwrite ChoiceBox css
	// TODO: Enable/disable percentages ChoiceBox

// TODO: Undecorated window

public class Main extends Application {	
	@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new VaccinationsScene();
		CSSUtils.addStylesheetsToParent(root);
		primaryStage.setTitle("COVID-19 Dashboard");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }

    public static void main(String[] args) {

    	
		launch(args);
    }
}
