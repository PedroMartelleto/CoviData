package com.dashboard.data.providers;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.data.globals.BrazilGlobals;
import com.dashboard.data.importer.CovidDataImporter;
import com.dashboard.data.importer.cssegiSandOwid.CSSEGISandOwidImporter;
import com.dashboard.data.model.XYChartDataModel;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 * Provides data for time series graphs. The maps are created externally in their respective
 * controllers, then fed the data from this class.
 */
public class TimeSeriesDataProvider {
	private CovidDataImporter importer;
	
	public int vaccinatedLast7DaysNumber = 0;
	public int oneDosesNumber = 0;
	public int twoDosesNumber = 0;
	public float monthsTo70percent = 0.0f;
	
	public int totalCases = 0;
	public int totalDeaths = 0;
	
	public TimeSeriesDataProvider(CovidDataImporter importer) {
		this.importer = importer;
	}

	/**
	 * Provides data for the accumulated vaccine doses chart.
	 * 
	 * @param chart Destination chart.
	 * @param stride Number of neighbors from which to average.
	 * @param relativeNumbers Set this to true to display a graph in percentages.
	 */
	public void vaccinations(XYChart<String, Number> chart, int stride, boolean relativeNumbers) {
		XYChartDataModel[] data = importer.getVaccinationsLineChart();
		calculateVaccinationConstants(data);
		chart.setAnimated(true);
		provideLabels(chart, "Vacinação", "Data", "Quantidade");
		
		float normFactor = relativeNumbers ? BrazilGlobals.getBrazilPopulation() : 1;
		String chartNameSuffix = relativeNumbers ? "(números relativos)" : "(números absolutos)"; 
		provideSeries(chart, "Pessoas vacinadas " + chartNameSuffix, data[1].getPoints(), stride, normFactor, true);
		provideSeries(chart, "Pessoas totalmente vacinadas " + chartNameSuffix, data[2].getPoints(), stride, normFactor, true);
	}
	
	/**
	 * Updates local variables using the data provided for the charts. These are the numbers displayed in the GUI's labels.
	 * @param data
	 */
	private void calculateVaccinationConstants(XYChartDataModel[] data) {
		List<XYChart.Data<String, Number>> partiallyVaccinated = data[1].getPoints();
		List<XYChart.Data<String, Number>> totallyVaccinated = data[2].getPoints();
		
		int lengthTemp = partiallyVaccinated.size();
		Number vaccinatedLastDay = partiallyVaccinated.get(lengthTemp - 1).getYValue();
		Number vaccinated7DaysBefore =  partiallyVaccinated.get(lengthTemp - 8).getYValue();
		
		// Number of people vaccinated during the last 7 days.
		vaccinatedLast7DaysNumber = vaccinatedLastDay.intValue() - vaccinated7DaysBefore.intValue();
		oneDosesNumber = vaccinatedLastDay.intValue(); // Number of people w/ first dose
		
		lengthTemp = totallyVaccinated.size();
		
		twoDosesNumber = totallyVaccinated.get(lengthTemp - 1).getYValue().intValue(); // Number of people w/ second dose
		
		// Approximation of number of months to get to 70% fully vaccinated
		int speed = twoDosesNumber - totallyVaccinated.get(lengthTemp - 8).getYValue().intValue();
		float nWeeks = ((0.7f*BrazilGlobals.getBrazilPopulation()) -  twoDosesNumber) / speed;
		monthsTo70percent = nWeeks / 4.0f;
	}
	
	/**
	 * Provides data for the accumulated cases chart.
	 * 
	 * @param chart Destination chart.
	 * @param stride Number of neighbors from which to average.
	 * @param stateName The state from which to get the data.
	 */
	public void cases(XYChart<String, Number> chart, int stride, String stateName) {
		XYChartDataModel data = importer.getDailyCasesLineChart(stateName);
		chart.setAnimated(true);
		provideLabels(chart, "Casos", "Data", "Quantidade");
		provideSeries(chart, "Casos", data.getPoints(), stride, 1, true);
		totalCases = CSSEGISandOwidImporter.totalCases;
	}
	
	/**
	 * Provides data for the accumulated deaths chart.
	 * 
	 * @param chart Destination chart.
	 * @param stride Number of neighbors from which to average.
	 * @param stateName The state from which to get the data.
	 */
	public void deaths(XYChart<String, Number> chart, int stride, String stateName) {
		XYChartDataModel data = importer.getDailyDeathsLineChart(stateName);
		chart.setAnimated(true);
		provideLabels(chart, "Mortes", "Data", "Quantidade");
		provideSeries(chart, "Mortes", data.getPoints(), stride, 1, true);
		totalDeaths = CSSEGISandOwidImporter.totalDeaths;
	}
	
	/**
	 * Provides labels for dest chart.
	 * 
	 * @param destChart Destination chart.
	 * @param title Title of the graph.
	 * @param xLabel X-axis label.
	 * @param yLabel Y-axis label.
	 */
	private void provideLabels(XYChart<String, Number> destChart, String title, String xLabel, String yLabel) {
		destChart.getXAxis().setLabel(xLabel);
		destChart.getXAxis().setLabel(yLabel);
		destChart.setTitle(title);
	}
	
	/**
	 * Provides time series data to the destination chart. The resulting data is divided by norm factor and is interpolated using neighbor points.
	 * Stride defines the number of neighbor points to use.
	 * 
	 * @param destChart The destination chart.
	 * @param name The name of the series.
	 * @param data The time series data.
	 * @param stride the number of neighbors from which to take the mean.
	 * @param normFactor the data is divided by this factor.
	 * @param isAscending if true, enforces the time series to be ascending.
	 */
	private void provideSeries(XYChart<String, Number> destChart, String name,
			ArrayList<Data<String, Number>> data, int stride, float normFactor, boolean isAscending) {		
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(name);

		if (data.size() <= 0) return;
		
		for (int i = 0; i < data.size() - 1; i += stride) {
			// Interpolates the values by taking the mean
			float interpolatedValue = 0.0f;
			int total = 0;
			
			for (int j = i; j < i + stride; ++j) {
				if (j < data.size()) {
					int value = (int) data.get(j).getYValue();
					
					// Ensures time series is ascending (when isAscending is true).
					if (j > 0 && isAscending) {
						int previousValue = (int) data.get(j-1).getYValue();
						if (value <= previousValue) {
							value = previousValue;
						}
					}
					
					total += 1;
					interpolatedValue += value;
				}
			}
			
			interpolatedValue /= (total * normFactor);
			
			String label = data.get(Math.min(data.size()-1, i + stride/2)).getXValue();
			series.getData().add(new XYChart.Data<String, Number>(label, interpolatedValue));
		}
		
		destChart.getData().add(series);
	}
}
