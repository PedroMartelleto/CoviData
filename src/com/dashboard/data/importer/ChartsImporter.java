package com.dashboard.data.importer;

import java.util.List;

import com.dashboard.data.interfaces.ChartsInterface;
import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;
import com.dashboard.data.parser.CsvParser;

public class ChartsImporter implements ChartsInterface {

	@Override
	public LineChartDataModel getVaccinationsLineChart(String stateName) {
		LineChartDataModel data = new LineChartDataModel();
		
		String csvNotParsed = Requests.getTotalVaccinated();
		List<String[]> csvParsed = CsvParser.getAllContent(csvNotParsed);
		
		for (String[] line: csvParsed) {
			if (line[0].equals("Brazil")) {
				String day = line[1];
				int value = line[5].length() > 0 ? Integer.valueOf(line[4]) : 0;
				data.addPoint(day, value);
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
		String csvNotParsed = Requests.getLastDailyReport();
		
		List<String[]> csvParse = CsvParser.getAllContent(csvNotParsed);
		
		for(String[] line: csvParse) {
			if(line[3].equals("Brazil")) {
				data.addPin(Double.valueOf(line[5]),Double.valueOf(line[6]),Integer.valueOf(line[7]), line[2]);
			}
		}
		return data;
	}

	@Override
	public MapChartDataModel getDeathsMapChart() {
		MapChartDataModel data = new MapChartDataModel();
		String csvNotParsed = Requests.getLastDailyReport();
		
		List<String[]> csvParse = CsvParser.getAllContent(csvNotParsed);
		
		for(String[] line: csvParse) {
			if(line[3].equals("Brazil")) {
				data.addPin(Double.valueOf(line[5]), Double.valueOf(line[6]), Integer.valueOf(line[8]), line[2]);
			}
		}
		return data;
	}

}
