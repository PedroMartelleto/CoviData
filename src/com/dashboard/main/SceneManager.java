package com.dashboard.main;

import com.dashboard.components.scenes.infected.InfectedScene;
import com.dashboard.components.scenes.newsReports.NewsReportsScene;
import com.dashboard.components.scenes.vaccinations.VaccinationsScene;
import com.dashboard.components.scenes.vaccineSchedule.VaccineScheduleScene;
import com.dashboard.utils.CSSUtils;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Scene manager with a singleton to each scene type.
 */
public class SceneManager {
	private static Scene vaccinations = null;
	private static Scene infected = null;
	private static Scene newsReport = null;
	private static Scene vaccineSchedule = null;
	
	public static Scene getVaccinations() {
		if (vaccinations == null) vaccinations = initScene(new VaccinationsScene());
		return vaccinations;
	}
	
	public static Scene getInfected() {
		if (infected == null) infected = initScene(new InfectedScene());
		return infected;
	}
	
	public static Scene getNewsReport() {
		if (newsReport == null) newsReport = initScene(new NewsReportsScene());
		return newsReport;
	}
	
	public static Scene getVaccineSchedule() {
		if (vaccineSchedule == null) vaccineSchedule = initScene(new VaccineScheduleScene());
		return vaccineSchedule;
	}
	
	/**
	 * Call this to force every scene to be loaded immediately.
	 */
	public static void init() {
		vaccinations = getVaccinations();
		infected = getInfected();
		newsReport = getNewsReport();
		vaccineSchedule = getVaccineSchedule();
	}
	
	private static Scene initScene(Parent parent) {
		CSSUtils.addStylesheetsToParent(parent);
		return new Scene(parent);
	}
}
