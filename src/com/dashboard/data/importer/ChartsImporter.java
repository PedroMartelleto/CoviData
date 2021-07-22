package com.dashboard.data.importer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dashboard.data.interfaces.ChartsInterface;
import com.dashboard.data.model.LineChartDataModel;
import com.dashboard.data.model.MapChartDataModel;
import com.dashboard.data.parser.CsvParser;
import com.sothawo.mapjfx.Coordinate;


public class ChartsImporter implements ChartsInterface {

	@Override
	public LineChartDataModel GetVaccinationsLineChart() {
		LineChartDataModel data = new LineChartDataModel();
		
		String csvNotParsed = Requests.getTotalVaccinated();
		
		List<String[]> csvParsed= CsvParser.getAllContent(csvNotParsed);
		
		for(String[] line: csvParsed) {
			if(line[0].equals("Brazil")) {
				String day = line[1];
				int value = line[5].length() > 0 ? Integer.valueOf(line[4]) : 0;
				
				data.AddDot(day, value);
			}
		}
		
		
		return data;
	}

	@Override
	public LineChartDataModel GetDailyTotalCasesLineChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineChartDataModel[] GetDailyStateCasesLineChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineChartDataModel GetDailyTotalDeathsLineChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineChartDataModel[] GetDailyStateDeathsLineChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapChartDataModel GetCasesMapChart() {
		MapChartDataModel data = new MapChartDataModel();
		String csvNotParsed = Requests.getLastDailyReport();
		
		List<String[]> csvParse = CsvParser.getAllContent(csvNotParsed);
		
		for(String[] line: csvParse) {
			if(line[3].equals("Brazil")) {
				data.AddPin(Double.valueOf(line[5]),Double.valueOf(line[6]),Integer.valueOf(line[7]), line[2]);
			}
		}
		return data;
	}

	@Override
	public MapChartDataModel GetDeathsMapChart() {
		MapChartDataModel data = new MapChartDataModel();
		String csvNotParsed = Requests.getLastDailyReport();
		
		List<String[]> csvParse = CsvParser.getAllContent(csvNotParsed);
		
		for(String[] line: csvParse) {
			if(line[3].equals("Brazil")) {
				data.AddPin(Double.valueOf(line[5]),Double.valueOf(line[6]),Integer.valueOf(line[8]), line[2]);
			}
		}
		return data;
	}

}
