package com.dashboard.main;

import javafx.application.Application;
import javafx.stage.Stage;;

public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		// Scenes.init();
		
		primaryStage.setTitle("COVID-19 Dashboard");
		primaryStage.setScene(Scenes.getVaccinations());
		primaryStage.setWidth(1024);
		primaryStage.setHeight(700);
		primaryStage.show();
    }

    public static void main(String[] args) {
		launch(args);
    }
}
