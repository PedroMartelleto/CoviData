package com.dashboard.main;

import java.io.IOException;

import com.dashboard.data.importer.DataFiles;

import javafx.application.Application;
import javafx.stage.Stage;;

public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception {	
		primaryStage.setTitle("COVID-19 Dashboard");
		primaryStage.setScene(Scenes.getVaccinations());
		primaryStage.setWidth(1024);
		primaryStage.setHeight(700);
		primaryStage.show();
    }

    public static void main(String[] args) {
    	// Make sure that the user has the most recent data
    	try {    		
    		DataFiles.writeMissedData();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// Launches the file
		launch(args);
    }
}
