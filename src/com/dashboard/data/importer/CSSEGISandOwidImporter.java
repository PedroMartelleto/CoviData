package com.dashboard.data.importer;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javafx.util.Pair;

import com.dashboard.data.common.BrazilData;
import com.dashboard.data.interfaces.ChartsInterface;
import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;
import com.dashboard.data.parser.CsvParser;


/**
 * Reads COVID-19 data from CSSEGI and OWID.
 */
public class CSSEGISandOwidImporter implements ChartsInterface {
	public static int n_deaths = 0;
	public static int n_cases = 0;
	
	private static ArrayList<String> states = BrazilData.getStateNames();
	private static String ALLSTATES = states.get(0);
	
	/**
	 * Parses a CSSEGISandData-downloaded csv and returns relevant vaccination information.
	 */
	@Override
	public LineChartDataModel[] getVaccinationsLineChart() {
		LineChartDataModel[] data = new LineChartDataModel[3];

		for (int i = 0; i < 3; i++) {
			data[i] = new LineChartDataModel();
		}

		String csvNotParsed = Requests.getTotalVaccinated();
		List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);

		for (String[] line : csvParsed) {
			if (line[0].equals("Brazil")) {
				String day = line[1];
				int value1 = line[4].length() > 0 ? Integer.valueOf(line[4]) : 0;
				int value2 = line[5].length() > 0 ? Integer.valueOf(line[5]) : 0;
				int value3 = line[6].length() > 0 ? Integer.valueOf(line[6]) : 0;
				data[0].addPoint(day, value1);
				data[1].addPoint(day, value2);
				data[2].addPoint(day, value3);
			}
		}

		return data;
	}

	/**
	 * Reads the data and calculates the daily cases by Brazilian's states
	 * 
	 * @return Map<state, data>
	 */
	@Override
	public LineChartDataModel getDailyCasesLineChart(String stateName) {
		Map<String, List<String[]>> data = DataFiles.readAllData();
		LineChartDataModel chart = new LineChartDataModel();

		int index = states.indexOf(stateName) - 1;
		// boolean allStates = stateName.equals(BrazilData.ALL_STATES);
		int yesterday = 0;
		int total = 0;;
		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			total = 0;
			
			if(index == -1) {
				for (String[] line : file.getValue()) {
					total += Integer.parseInt(line[4]);
				}
			}
			else {
				if(file.getValue().size() > 1) {
					String[] line = file.getValue().get(index);
					total += Integer.parseInt(line[4]);
				}
				
			}
			
			chart.addPoint(file.getKey(), total - yesterday);
			yesterday = total;
		}

		
		n_cases = total;
		return chart;
	}

	/**
	 * Reads the data and calculates the daily deaths by Brazilian's states
	 * 
	 * @return Map<state, data>
	 */
	@Override
	public LineChartDataModel getDailyDeathsLineChart(String stateName) {
		Map<String, List<String[]>> data = DataFiles.readAllData();
		LineChartDataModel chart = new LineChartDataModel();

		int index = states.indexOf(stateName) - 1;
		// boolean allStates = stateName.equals(BrazilData.ALL_STATES);
		int yesterday = 0;
		int total = 0;
		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			total = 0;
			if(index == -1) {
				for (String[] line : file.getValue()) {
					total += Integer.parseInt(line[5]);
				}
			}
			else {
				if(file.getValue().size() > 1) {
					String[] line = file.getValue().get(index);
					total += Integer.parseInt(line[5]);
				}
				
			}
			
			chart.addPoint(file.getKey(), total - yesterday);
			yesterday = total;
		}

		n_deaths = total;
		return chart;
	}

	/**
	 * Get the average data between the last 2 weeks new cases
	 */
	@Override
	public MapChartDataModel getCasesMapChart() {
		// Setting default variables
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		MapChartDataModel data = new MapChartDataModel();

		// Setting structures for data control
		Map<String, int[]> values = new HashMap<String, int[]>();
		Map<String, Pair<Double, Double>> coordinates = new HashMap<String, Pair<Double, Double>>();
		for (String name : BrazilData.getStateNames()) {
			values.put(name, new int[3]);
		}

		// Getting the total number for the last 3 weeks
		long day = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		for (int i = 0; i < 3; i++) {
			String fileName = format.format(new Date(day)) + ".csv";
			String csvNotParsed = "";
			try {
				csvNotParsed = DataFiles.readData(fileName);
			} catch(IOException e) {
				day -= 24 * 60 * 60 * 1000;
				i--;
				continue;
			}

			List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);

			for (String[] line : csvParsed) {
				int[] valuesOfState = values.get(line[1]);

				valuesOfState[i] = Integer.valueOf(line[4]);

				if (i == 0) {
					coordinates.put(line[1], new Pair<Double, Double>(Double.valueOf(line[2]), Double.valueOf(line[3])));
				}
			}

			day -= 7 * 24 * 60 * 60 * 1000;
		}
		
		// Calculating the last 2 weeks increase 
		for (int[] valuesByState : values.values()) {
			valuesByState[0] = valuesByState[0] - valuesByState[1];
			valuesByState[1] = valuesByState[1] - valuesByState[2];
		}

		// Calculating the average between the 2 weeks
		for (Map.Entry<String, int[]> valuesAndState : values.entrySet()) {
			int[] valuesOfState = valuesAndState.getValue();
			double thisWeek = valuesOfState[0];
			double lastWeek = valuesOfState[1];

			double average = thisWeek / lastWeek;

			Pair<Double, Double> latLong = coordinates.get(valuesAndState.getKey());

			if (latLong != null) {
				data.addPin(latLong.getKey(), latLong.getValue(), average);
			}

		}

		// Returning the average values
		return data;
	}

	/**
	 * Get the average data between the last 2 weeks new deaths
	 */
	@Override
	public MapChartDataModel getDeathsMapChart() {
		// Setting default variables
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		MapChartDataModel data = new MapChartDataModel();

		// Setting structures for data control
		Map<String, int[]> values = new HashMap<String, int[]>();
		Map<String, Pair<Double, Double>> coordinates = new HashMap<String, Pair<Double, Double>>();
		for (String name : BrazilData.getStateNames()) {
			values.put(name, new int[3]);
		}

		// Getting the total number for the last 3 weeks
		long day = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		for (int i = 0; i < 3; i++) {
			String fileName = format.format(new Date(day)) + ".csv";
			String csvNotParsed = "";
			try {
				csvNotParsed = DataFiles.readData(fileName);
			} catch(IOException e) {
				day -= 24 * 60 * 60 * 1000;
				i--;
				continue;
			}

			List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);

			for (String[] line : csvParsed) {
				int[] valuesOfState = values.get(line[1]);

				valuesOfState[i] = Integer.valueOf(line[5]);

				if (i == 0) {
					coordinates.put(line[1], new Pair<Double, Double>(Double.valueOf(line[2]), Double.valueOf(line[3])));
				}
			}

			day -= 7 * 24 * 60 * 60 * 1000;
		}
		
		// Calculating the last 2 weeks increase 
		for (int[] valuesByState : values.values()) {
			valuesByState[0] = valuesByState[0] - valuesByState[1];
			valuesByState[1] = valuesByState[1] - valuesByState[2];
		}

		// Calculating the average between the 2 weeks
		for (Map.Entry<String, int[]> valuesAndState : values.entrySet()) {
			int[] valuesOfState = valuesAndState.getValue();
			double thisWeek = valuesOfState[0];
			double lastWeek = valuesOfState[1];

			double average = thisWeek / lastWeek;

			Pair<Double, Double> latLong = coordinates.get(valuesAndState.getKey());

			if (latLong != null) {
				data.addPin(latLong.getKey(), latLong.getValue(), average);
			}

		}

		// Returning the average values
		return data;
	}

}
