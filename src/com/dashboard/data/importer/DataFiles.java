package com.dashboard.data.importer;

import com.dashboard.data.parser.CsvParser;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class DataFiles {
	public Boolean writeData(String fileName, List<String[]> data) {
		File file = new File ("data/" + fileName);
		FileWriter fw;
		BufferedWriter bw;
		
		// Ensures that the file will be empty
		if (file.exists())	file.delete();
			
		try {				
			file.createNewFile();
			fw = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		
		
		bw = new BufferedWriter(fw);
		for (String[] line : data) {
			if (line[3].equals("Brazil")) {
				String state = line[2];
				String country = line[3];
				String lat = line[5];
				String lon = line[6];
				String cases = line[7];
				String deaths = line[8];
				
				try {
					bw.write(country + "," + state + "," + lat + "," + lon + "," + cases + "," + deaths + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		// Closes the opened streams
		try {			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		
		return false;
	}

	public List<String[]> readData(String fileName){
		return null;
	}
	
	public void readAllCases(String fileName) {
		new Requests();
		Map<String, String> contents = Requests.getAllCases();
		
		new CsvParser();
		for (Map.Entry<String, String> file : contents.entrySet()) {
			List<String[]> parsedContent = CsvParser.getAllContent(file.getValue());
			
			if (writeData(file.getKey(), parsedContent)) {
				System.out.print("foda\n");
			} else {
				System.out.print("Novo arquivo escrito: " + file.getKey() + "\n");
			}
		}
	}
}
