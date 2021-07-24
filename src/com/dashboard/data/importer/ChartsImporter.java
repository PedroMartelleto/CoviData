package com.dashboard.data.importer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.dashboard.data.common.BrazilData;
import com.dashboard.data.interfaces.ChartsInterface;
import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;
import com.dashboard.data.parser.CsvParser;

import javafx.util.Pair;

import java.text.*;

public class ChartsImporter implements ChartsInterface {
	

	@Override
	public LineChartDataModel[] getVaccinationsLineChart() {
		LineChartDataModel[] data = new LineChartDataModel[3];
		
		for(int i = 0; i < 3; i++) {
			data[i] = new LineChartDataModel();
		}
		
		String csvNotParsed = Requests.getTotalVaccinated();
		List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);
		
		for (String[] line: csvParsed) {
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

	@Override
	public LineChartDataModel getDailyTotalCasesLineChart(String stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineChartDataModel[] getDailyStateCasesLineChart(String stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineChartDataModel getDailyTotalDeathsLineChart(String stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineChartDataModel[] getDailyStateDeathsLineChart(String stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapChartDataModel getCasesMapChart() {
		MapChartDataModel data = new MapChartDataModel();

		Map<String, int[] > values= new HashMap<String, int[]>();
		Map<String, Pair<Double, Double>> coordinates = new HashMap<String, Pair<Double, Double>>();
		
		for(String name: BrazilData.getStateNames()) {
			values.put(name, new int[15]);
		}
		
		long day = System.currentTimeMillis() - 24*60*60*1000;
		
	

		for(int i = 0; i < 15; i++) {
			
			//leitura do csv do dia
			String csvNotParsed = Requests.getReportByDay(day);
			
			if(csvNotParsed != null) {
				List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);
				
				for(String[] line: csvParsed) {
					if(line[3].equals("Brazil")) {
						//System.out.println(line[2]);
						
						//grava valor do dia para estado correspondente
						int[] valuesOfState = values.get(line[2]);
						
						valuesOfState[i] = Integer.valueOf(line[7]);
						
						if(i == 0) {
							coordinates.put(line[2], new Pair<Double, Double>(Double.valueOf(line[5]), Double.valueOf(line[6])));
						}
					}
				}
			}	
			day -= 24*60*60*1000;
			//data.addPin(Double.valueOf(line[5]), Double.valueOf(line[6]), Integer.valueOf(line[8]), line[2]);
		}
		
		
		//obtenção dos dados de mortes diárias
		for(int[] valuesByState: values.values()) {
			for(int i = 0; i < 14; i++) {
				int actualValue = valuesByState[i];
				int beforeValue = valuesByState[i+1];
				
				valuesByState[i] = actualValue - beforeValue;
			}
		}
		
		
		//Fazendo média semanal 1 e 2 e pegando o aumento da semana atual para a semana anterior 
		for(Map.Entry<String, int[]> valuesAndState: values.entrySet()) {
			int[] valuesOfState = valuesAndState.getValue();
			double averageThisWeek = 0;
			double averageBeforeWeek = 0;
			
			for(int i = 0; i < 7; i++) {
				averageThisWeek += valuesOfState[i];
			}
			averageThisWeek = averageThisWeek / 7.0;
			
			for(int i = 7; i < 14; i++) {
				averageBeforeWeek += valuesOfState[i];
			}
			averageBeforeWeek = averageBeforeWeek / 7.0;
			
			
			double average = averageThisWeek/averageBeforeWeek;
			
			//System.out.println("Pegando chave: " + valuesAndState.getKey());
			Pair<Double, Double> latLong = coordinates.get(valuesAndState.getKey());
			
			if(latLong != null) {
				//System.out.println("Lat: " + latLong.getKey() + " " + "Long: " + latLong.getValue());
				data.addPin(latLong.getKey(), latLong.getValue(), average);

			}
			
		}
		
		
		
		return data;
	}

	@Override
	public MapChartDataModel getDeathsMapChart() {
		MapChartDataModel data = new MapChartDataModel();

		Map<String, int[] > values= new HashMap<String, int[]>();
		Map<String, Pair<Double, Double>> coordinates = new HashMap<String, Pair<Double, Double>>();
		
		for(String name: BrazilData.getStateNames()) {
			values.put(name, new int[15]);
		}
		
		long day = System.currentTimeMillis() - 24*60*60*1000;
		
	

		for(int i = 0; i < 15; i++) {
			
			//leitura do csv do dia
			String csvNotParsed = Requests.getReportByDay(day);
			
			if(csvNotParsed != null) {
				List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);
				
				for(String[] line: csvParsed) {
					if(line[3].equals("Brazil")) {
						//System.out.println(line[2]);
						
						//grava valor do dia para estado correspondente
						int[] valuesOfState = values.get(line[2]);
						
						valuesOfState[i] = Integer.valueOf(line[8]);
						
						if(i == 0) {
							coordinates.put(line[2], new Pair<Double, Double>(Double.valueOf(line[5]), Double.valueOf(line[6])));
						}
					}
				}
			}	
			day -= 24*60*60*1000;
			//data.addPin(Double.valueOf(line[5]), Double.valueOf(line[6]), Integer.valueOf(line[8]), line[2]);
		}
		
		
		//obtenção dos dados de mortes diárias
		for(int[] valuesByState: values.values()) {
			for(int i = 0; i < 14; i++) {
				int actualValue = valuesByState[i];
				int beforeValue = valuesByState[i+1];
				
				valuesByState[i] = actualValue - beforeValue;
			}
		}
		
		
		//Fazendo média semanal 1 e 2 e pegando o aumento da semana atual para a semana anterior 
		for(Map.Entry<String, int[]> valuesAndState: values.entrySet()) {
			int[] valuesOfState = valuesAndState.getValue();
			double averageThisWeek = 0;
			double averageBeforeWeek = 0;
			
			for(int i = 0; i < 7; i++) {
				averageThisWeek += valuesOfState[i];
			}
			averageThisWeek = averageThisWeek / 7.0;
			
			for(int i = 7; i < 14; i++) {
				averageBeforeWeek += valuesOfState[i];
			}
			averageBeforeWeek = averageBeforeWeek / 7.0;
			
			
			double average = averageThisWeek/averageBeforeWeek;
			
			//System.out.println("Pegando chave: " + valuesAndState.getKey());
			Pair<Double, Double> latLong = coordinates.get(valuesAndState.getKey());
			
			if(latLong != null) {
				//System.out.println("Lat: " + latLong.getKey() + " " + "Long: " + latLong.getValue());
				data.addPin(latLong.getKey(), latLong.getValue(), average);

			}
			
		}
		
		
		
		return data;
	}

}
