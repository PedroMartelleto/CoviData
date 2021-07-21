package com.dashboard.main;

import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.data.importer.Requests;
import com.dashboard.utils.CSSUtils;

import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

// TODO: Vaccines page
	// TODO: Overwrite ChoiceBox css
	// TODO: Enable/disable percentages ChoiceBox

// TODO: Undecorated window

public class Main extends Application {	
	@Override
    public void start(Stage primaryStage) throws Exception {
        CSSFX.start();
		
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
