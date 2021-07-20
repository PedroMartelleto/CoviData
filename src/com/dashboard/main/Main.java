package com.dashboard.main;

import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.data.importer.Requests;
import com.dashboard.utils.CSSUtils;

import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: Vaccines page
	// TODO: Overwrite ChoiceBox css
	// TODO: Enable/disable percentages ChoiceBox

// TODO: Undecorated window

// TODO: É possível pegar o cronograma usando APIs?

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
    	System.setProperty("prism.lcdtext", "false"); // Font anti-aliasing
    	Requests.vaccination();
		launch(args);
    }
}
