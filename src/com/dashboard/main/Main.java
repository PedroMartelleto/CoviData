package com.dashboard.main;

import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.utils.CSSUtils;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: CDMenuBar

// TODO: Vaccines page
	// TODO: Overwrite ChoiceBox css
	// TODO: Enable/disable percentages ChoiceBox

// TODO: É possível pegar o cronograma usando APIs?

// TODO: Multiple scenes https://www.youtube.com/watch?v=XCgcQTQCfJQ

public class Main extends Application {	
	@Override
    public void start(Stage primaryStage) throws Exception{
		Parent root = new VaccinationsScene();
		CSSUtils.addStylesheetsToParent(root);
		primaryStage.setTitle("COVID-19 Dashboard");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }

    public static void main(String[] args) {
    	System.setProperty("prism.lcdtext", "false"); // Font anti-aliasing
		launch(args);
    }
}
