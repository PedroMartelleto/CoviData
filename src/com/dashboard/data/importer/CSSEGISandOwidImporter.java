package com.dashboard.data.importer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dashboard.data.common.BrazilData;
import com.dashboard.data.interfaces.ChartsInterface;
import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;
import com.dashboard.data.parser.CsvParser;

import javafx.util.Pair;

/**
 * Reads COVID-19 data from CSSEGI and OWID.
 */
public class CSSEGISandOwidImporter implements ChartsInterface {
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
	 * Reads the CSSEGISandData and calculates the country daily cases.
	 */
	@Override
	public LineChartDataModel getDailyTotalCasesLineChart(String stateName) {
		Map<String, List<String[]>> data = DataFiles.readAllData();
		LineChartDataModel chart = new LineChartDataModel();

		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			int totalCases = 0;
			for (String[] line : file.getValue()) {
				totalCases += Integer.parseInt(line[4]);
			}
			String name = file.getKey();
			String date = name.substring(6, 9) + "-" + name.substring(0, 1) + "-" + name.substring(3, 4);

			chart.addPoint(date, totalCases);
		}

		return chart;
	}

	/**
	 * Reads the data and calculates the daily cases by Brazilian's states
	 * 
	 * @return Map<state, data>
	 */
	@Override
	public LineChartDataModel getDailyStateCasesLineChart(String stateName) {
		Map<String, List<String[]>> data = DataFiles.readAllData();
		LineChartDataModel chart = new LineChartDataModel();

		Map<String, List<String[]>> dataSorted = new TreeMap<>();

		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			String name = file.getKey();
			String date = name.substring(6, 10) + "-" + name.substring(0, 1) + "-" + name.substring(3, 4);
			dataSorted.put(date, file.getValue());
		}
		
		boolean allStates = stateName.equals(BrazilData.ALL_STATES);
		int lastDay = 0;
		for (Map.Entry<String, List<String[]>> file : dataSorted.entrySet()) {
			int totalCasesState = 0;
			
			for (String[] line : file.getValue()) {
				if (allStates || stateName.equals(line[1])) {
					System.out.println(line);
					totalCasesState += Integer.parseInt(line[4]) - lastDay;
					lastDay = Integer.parseInt(line[4]);
				}
			}
			chart.addPoint(file.getKey(), totalCasesState);
		}

		return chart;
	}

	/**
	 * Reads the data and calculates the daily deaths by Brazilian's states
	 * 
	 * @return Map<state, data>
	 */
	@Override
	public LineChartDataModel getDailyStateDeathsLineChart(String stateName) {
		Map<String, List<String[]>> data = DataFiles.readAllData();
		LineChartDataModel chart = new LineChartDataModel();

		Map<String, List<String[]>> dataSorted = new TreeMap<>();

		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			String name = file.getKey();
			String date = name.substring(6, 10) + "-" + name.substring(0, 1) + "-" + name.substring(3, 4);

			dataSorted.put(date, file.getValue());
		}

		int lastDay = 0;
		boolean allStates = stateName.equals(BrazilData.ALL_STATES);
		
		for (Map.Entry<String, List<String[]>> file : dataSorted.entrySet()) {
			int totalCasesState = 0;
			for (String[] line : file.getValue()) {
				if (allStates || stateName.equals(line[1])) {
					try {
						totalCasesState += Integer.parseInt(line[5]) - lastDay;
						lastDay = Integer.parseInt(line[5]);
					} catch (Exception e) {}
				}
			}
			chart.addPoint(file.getKey(), totalCasesState);
		}

		return chart;

	}


	/**
	 * Reads the data and calculates the country daily deaths
	 */
	@Override
	public LineChartDataModel getDailyTotalDeathsLineChart(String stateName) {
		Map<String, List<String[]>> data = DataFiles.readAllData();
		LineChartDataModel chart = new LineChartDataModel();

		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			int totalDeaths = 0;
			for (String[] line : file.getValue()) {
				totalDeaths += Integer.parseInt(line[5]);
			}
			String name = file.getKey();
			String date = name.substring(6, 9) + "-" + name.substring(0, 1) + "-" + name.substring(3, 4);

			chart.addPoint(date, totalDeaths);
		}

		return chart;
	}
	
	/**
	 * 
	 */
	@Override
	public MapChartDataModel getCasesMapChart() {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		MapChartDataModel data = new MapChartDataModel();

		Map<String, int[]> values = new HashMap<String, int[]>();
		Map<String, Pair<Double, Double>> coordinates = new HashMap<String, Pair<Double, Double>>();

		for (String name : BrazilData.getStateNames()) {
			values.put(name, new int[15]);
		}

		long day = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		
		for (int i = 0; i < 15; i++) {
			// leitura do csv do dia
			String fileName = format.format(new Date(day)) + ".csv";
			String csvNotParsed = "";
			try {
				csvNotParsed = DataFiles.readData(fileName);
			} catch(IOException e) {
				continue;
			}
				

			List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);

			for (String[] line : csvParsed) {
				// grava valor do dia para estado correspondente
				int[] valuesOfState = values.get(line[1]);

				valuesOfState[i] = Integer.valueOf(line[4]);

				if (i == 0) {
					coordinates.put(line[1], new Pair<Double, Double>(Double.valueOf(line[2]), Double.valueOf(line[3])));
				}
			}

			day -= 24 * 60 * 60 * 1000;
			
			// data.addPin(Double.valueOf(line[5]), Double.valueOf(line[6]),
			// Integer.valueOf(line[8]), line[2]);
		}
				
		// obtenção dos dados de mortes diárias
		for (int[] valuesByState : values.values()) {
			for (int i = 0; i < 14; i++) {
				int actualValue = valuesByState[i];
				int beforeValue = valuesByState[i + 1];

				valuesByState[i] = actualValue - beforeValue;
			}
		}

		// Fazendo média semanal 1 e 2 e pegando o aumento da semana atual para a semana
		// anterior
		for (Map.Entry<String, int[]> valuesAndState : values.entrySet()) {
			int[] valuesOfState = valuesAndState.getValue();
			double averageThisWeek = 0;
			double averageBeforeWeek = 0;

			for (int i = 0; i < 7; i++) {
				averageThisWeek += valuesOfState[i];
			}
			averageThisWeek = averageThisWeek / 7.0;

			for (int i = 7; i < 14; i++) {
				averageBeforeWeek += valuesOfState[i];
			}
			averageBeforeWeek = averageBeforeWeek / 7.0;

			double average = averageThisWeek / averageBeforeWeek;

			// System.out.println("Pegando chave: " + valuesAndState.getKey());
			Pair<Double, Double> latLong = coordinates.get(valuesAndState.getKey());

			if (latLong != null) {
				// System.out.println("Lat: " + latLong.getKey() + " " + "Long: " +
				// latLong.getValue());
				data.addPin(latLong.getKey(), latLong.getValue(), average);

			}

		}

		return data;
	}

	@Override
	public MapChartDataModel getDeathsMapChart() {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		MapChartDataModel data = new MapChartDataModel();

		Map<String, int[]> values = new HashMap<String, int[]>();
		Map<String, Pair<Double, Double>> coordinates = new HashMap<String, Pair<Double, Double>>();

		for (String name : BrazilData.getStateNames()) {
			values.put(name, new int[15]);
		}

		long day = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		
		for (int i = 0; i < 15; i++) {
			// leitura do csv do dia
			String fileName = format.format(new Date(day)) + ".csv";
			System.out.print(fileName + "\n");
			String csvNotParsed = "";
			try {
				csvNotParsed = DataFiles.readData(fileName);
			} catch(IOException e) {
				continue;
			}
				

			List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);

			for (String[] line : csvParsed) {
				// grava valor do dia para estado correspondente
				int[] valuesOfState = values.get(line[1]);

				System.out.print(line[5] + "\n\n");
				valuesOfState[i] = Integer.valueOf(line[5]);

				if (i == 0) {
					coordinates.put(line[1], new Pair<Double, Double>(Double.valueOf(line[2]), Double.valueOf(line[3])));
				}
			}

			day -= 24 * 60 * 60 * 1000;
			
			// data.addPin(Double.valueOf(line[5]), Double.valueOf(line[6]),
			// Integer.valueOf(line[8]), line[2]);
		}
				
		// obtenção dos dados de mortes diárias
		for (int[] valuesByState : values.values()) {
			for (int i = 0; i < 14; i++) {
				int actualValue = valuesByState[i];
				int beforeValue = valuesByState[i + 1];

				valuesByState[i] = actualValue - beforeValue;
			}
		}

		// Fazendo média semanal 1 e 2 e pegando o aumento da semana atual para a semana
		// anterior
		for (Map.Entry<String, int[]> valuesAndState : values.entrySet()) {
			int[] valuesOfState = valuesAndState.getValue();
			double averageThisWeek = 0;
			double averageBeforeWeek = 0;

			for (int i = 0; i < 7; i++) {
				averageThisWeek += valuesOfState[i];
			}
			averageThisWeek = averageThisWeek / 7.0;

			for (int i = 7; i < 14; i++) {
				averageBeforeWeek += valuesOfState[i];
			}
			averageBeforeWeek = averageBeforeWeek / 7.0;

			double average = averageThisWeek / averageBeforeWeek;

			// System.out.println("Pegando chave: " + valuesAndState.getKey());
			Pair<Double, Double> latLong = coordinates.get(valuesAndState.getKey());

			if (latLong != null) {
				// System.out.println("Lat: " + latLong.getKey() + " " + "Long: " +
				// latLong.getValue());
				data.addPin(latLong.getKey(), latLong.getValue(), average);

			}

		}

		return data;
	}

}