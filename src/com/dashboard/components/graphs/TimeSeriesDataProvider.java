package com.dashboard.components.graphs;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.data.common.BrazilData;
import com.dashboard.data.importer.ChartsImporter;
import com.dashboard.data.model.LineChartDataModel;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class TimeSeriesDataProvider {
	private static LineChartDataModel[] data;
	private static ChartsImporter importer = new ChartsImporter();
	
	public static int vaccinatedLast7DaysNumber = 0;
	public static int oneDosesNumber = 0;
	public static int twoDosesNumber = 0;
	public static float monthsTo70percent = 0.0f;

	public static void vaccinations(XYChart<String, Number> chart, int stride, boolean relativeNumbers) {
		data = importer.getVaccinationsLineChart();
		getNumbers();
		chart.setAnimated(true);
		provideLabels(chart, "Vacinação acumulada por dia", "Data", "Quantidade");
		
		float normFactor = relativeNumbers ? BrazilData.getBrazilPopulation() : 1;
		String chartNameSuffix = relativeNumbers ? "(números relativos)" : "(números absolutos)"; 
		provideSeries(chart, "Pessoas vacinadas " + chartNameSuffix, data[1].getPoints(), stride, normFactor, true);
		provideSeries(chart, "Pessoas totalmente vacinadas " + chartNameSuffix, data[2].getPoints(), stride, normFactor, true);
	}
	
	private static void getNumbers() {
		List<XYChart.Data<String, Number>> partiallyVaccinated = data[1].getPoints();
		List<XYChart.Data<String, Number>> totallyVaccinated = data[2].getPoints();
		
		int lengthTemp = partiallyVaccinated.size();
		Number vaccinatedLastDay = partiallyVaccinated.get(lengthTemp - 1).getYValue();
		Number vaccinated7DaysBefore =  partiallyVaccinated.get(lengthTemp - 8).getYValue();
		vaccinatedLast7DaysNumber = vaccinatedLastDay.intValue() - vaccinated7DaysBefore.intValue();
		
				
		oneDosesNumber = vaccinatedLastDay.intValue();
		
		lengthTemp = totallyVaccinated.size();
		
		twoDosesNumber = totallyVaccinated.get(lengthTemp - 1).getYValue().intValue();
		
		int speed = twoDosesNumber - totallyVaccinated.get(lengthTemp - 8).getYValue().intValue();
		
		float nWeeks = ((0.7f*BrazilData.getBrazilPopulation()) -  twoDosesNumber) / speed;
		
		monthsTo70percent = nWeeks / 4.0f;
		
	}
	
	private static void provideLabels(XYChart<String, Number> destChart, String title, String xLabel, String yLabel) {
		destChart.getXAxis().setLabel(xLabel);
		destChart.getXAxis().setLabel(yLabel);
		destChart.setTitle(title);
	}
	
	/**
	 * Provides time series data to the destination chart. The resulting data is divided by norm factor and is interpolated using neighbor points.
	 * Stride defines the number of neighbor points to use.
	 * 
	 * @param destChart
	 * @param name
	 * @param data
	 * @param stride the number of neighbors from which to take the mean.
	 * @param normFactor the data is divided by this factor.
	 * @param isAscending if true, enforces the time series to be ascending.
	 */
	private static void provideSeries(XYChart<String, Number> destChart, String name,
			ArrayList<Data<String, Number>> data, int stride, float normFactor, boolean isAscending) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		
		for (int i = 0; i < data.size() - 1; i += stride) {
			// Interpolates the values by taking the mean
			float interpolatedValue = 0.0f;
			
			for (int j = i; j < data.size() - 1 && j < (i + stride); ++j) {
				int value = (int) data.get(j).getYValue();
				
				// Ensures time series is ascending (when isAscending is true).
				if (j > 0 && isAscending) {
					int previousValue = (int) data.get(j-1).getYValue();
					if (value <= previousValue) {
						value = previousValue;
					}
				}
				
				interpolatedValue += value;
			}
			
			interpolatedValue /= (stride * normFactor);
			
			String label = data.get(i).getXValue();
			series.getData().add(new XYChart.Data<String, Number>(label, interpolatedValue));
		}
		
		series.setName(name);
		destChart.getData().add(series);
	}

}
