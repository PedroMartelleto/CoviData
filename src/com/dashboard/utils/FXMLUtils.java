package com.dashboard.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Helper class that facilitates loading FXMLs in Controllers.
 * @author Pedro
 */
public class FXMLUtils {
	/**
	 * Initializes the given class as a controller.
	 * Assumes that its fxml is in the same folder and has the same name as the class.
	 * 
	 * @param <T>
	 * @param component
	 */
	public static <T extends Parent> void loadFXML(T component) {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setRoot(component);
	    loader.setControllerFactory(theClass -> component);

	    String fileName = component.getClass().getSimpleName() + ".fxml";
	    try {
	        loader.load(component.getClass().getResourceAsStream(fileName));
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}