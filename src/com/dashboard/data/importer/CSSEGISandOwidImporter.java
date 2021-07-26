package com.dashboard.data.importer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import javafx.scene.chart.XYChart.Data;
import javafx.util.Pair;

/**
 * Reads COVID-19 data from CSSEGI and OWID.
 */
public class CSSEGISandOwidImporter implements ChartsInterface {
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
		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			int total = 0;
			
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
		}

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

		// boolean allStates = stateName.equals(BrazilData.ALL_STATES);
		int yesterday = 0;
		for (Map.Entry<String, List<String[]>> file : data.entrySet()) {
			int total = 0;
			
			
			// TODO: O(n) -> O(1);
			// estado:	receber um int que passa a linha do estado buscado
			// todos:	última linha vai ser a soma de todos os outros
			for (String[] line : file.getValue()) {
				total += Integer.parseInt(line[5]);
			}
			chart.addPoint(file.getKey(), total - yesterday);
		}
		
		for (Data<String, Number> point : chart.points) {
			System.out.print(point.getYValue() + " " + point.getXValue().toString() + "\n");
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
