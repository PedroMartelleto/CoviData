package com.dashboard.data.providers;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.data.importer.CovidDataImporter;
import com.dashboard.data.importer.cssegiSandOwid.CSSEGISandOwidImporter;
import com.dashboard.data.model.MapDataModel;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;

import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * Provides data for maps. The maps are created externally in their respective
 * controllers, then fed the data from this class.
 */
public class MapDataProvider {
	private static MapDataModel data;
	private static CovidDataImporter importer;
	
	public MapDataProvider(CovidDataImporter importer) {
		this.importer = importer;
	}
	
	/**
	 * Returns a list of circles with size proportional to the increase in deaths,
	 * for each state.
	 * 
	 * @return
	 */
	public List<MapCircle> deathsByState() {
		data = importer.getDeathsMapChart();
		return getCircles(data.getPins());
	}

	/**
	 * Returns a list of circles with size proportional to the increase in cases,
	 * for each state.
	 * 
	 * @return
	 */
	public List<MapCircle> casesByState() {
		data = importer.getCasesMapChart();
		return getCircles(data.getPins());
	}

	/**
	 * Given a list of pairs (coordinate, circleSize), returns a list of map circles
	 * that matches those properties.
	 * 
	 * @param data
	 * @return
	 */
	private List<MapCircle> getCircles(List<Pair<Coordinate, Double>> data) {
		List<MapCircle> circles = new ArrayList<MapCircle>();

		for (Pair<Coordinate, Double> entry : data) {
			Coordinate coordinate = entry.getKey();
			double increase = entry.getValue();
			Pair<Double, Color> circleSettings = classifyIncrease(increase);
			circles.add(new MapCircle(coordinate, circleSettings.getKey()).setFillColor(circleSettings.getValue())
					.setColor(circleSettings.getValue()).setVisible(true));
		}
		return circles;
	}
	
	/**
	 * Categorizes increase in three different intervals (red, yellow and blue). For
	 * each, performs a linear interpolation to calculate the size of the circle.
	 * The color is determined by the category.
	 * 
	 * @param increase to be categorized.
	 * @return Pair<Double, Color>
	 */
	private Pair<Double, Color> classifyIncrease(double increase) {
		double circleSize = 0;
		double normalValue = 0;
		Color c = Color.WHITE;

		// Blue
		if (increase >= 0 && increase <= 0.8) {
			normalValue = (increase) / 0.8 + 0.05;
			c = new Color(0.08, 0.08, 0.8, 0.6);
		} else if (increase > 0.8 && increase <= 1.2) { // Yellow
			normalValue = (increase - 0.8) / 0.4 + 0.05;
			c = new Color(0.8, 0.8, 0.08, 0.6);
		} else { // Red
			normalValue = (increase - 1.2) / 0.8 + 0.05;
			c = new Color(0.8, 0.08, 0.08, 0.6);
		}

		circleSize = normalValue * 150000;
		return new Pair<Double, Color>(circleSize, c);
	}
}
