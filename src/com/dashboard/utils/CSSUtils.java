package com.dashboard.utils;

import javafx.scene.Parent;

/**
 * Utils for loadings CSS files.
 */
public class CSSUtils {
	/**
	 * Stylesheets common to all scenes.
	 */
	private static final String[] globalStylesheets = { "DashboardMenuBar", "Core" };
	
	/**
	 * Loads all required CSS stylesheets to the given parent.
	 * @param parent
	 */
	public static void addStylesheetsToParent(Parent parent) {
		for (String stylesheet : globalStylesheets) {
			parent.getStylesheets().add("com/dashboard/styles/" + stylesheet + ".css");
		}
		parent.getStylesheets().add("com/dashboard/styles/" + parent.getClass().getSimpleName() + ".css");
	}
}
