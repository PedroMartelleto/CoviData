package com.dashboard.data.importer;

import java.io.IOException;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

import com.dashboard.data.parser.CsvParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class DataFiles {

	/**
	 * Writes in disk the file with the selected data
	 * 
	 * @param fileName The name of the data file to be written
	 * @param data     The data to be written
	 * 
	 * @return true (1) -> error, false (0) -> success
	 */
	public static Boolean writeData(String fileName, List<String[]> data) {
		// Creates the data full path
		File file = new File("data/" + fileName);
		FileWriter fw;
		BufferedWriter bw;

		// Ensures that the file will be empty
		if (file.exists())
			file.delete();

		// Creates the file to be written
		try {
			file.createNewFile();
			fw = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		bw = new BufferedWriter(fw);

		// Writes the fields in the file
		for (String[] line : data) {
			// We will only use the Brazil data
			if (line[3].equals("Brazil")) {
				String state = line[2];
				String country = line[3];
				String lat = line[5];
				String lon = line[6];
				String cases = line[7];
				String deaths = line[8];

				try {
					bw.write(country + "," + state + "," + lat + "," + lon + "," + cases + "," + deaths + ",\r\n");
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

	/**
	 * Read and write in disk every file from the case/death API
	 */
	public void writeAllData() {
		new Requests();
		new CsvParser();

		// Requests to the API the contents
		Map<String, String> contents = Requests.getAllCases();

		// For each file obtained, parse the content and writes in disk
		for (Map.Entry<String, String> file : contents.entrySet()) {
			List<String[]> parsedContent = CsvParser.getAllContent(file.getValue());

			if (writeData(file.getKey(), parsedContent)) {
				System.out.print("Falha na escrita do arquivo: " + file.getKey() + "\n");
			} else {
				System.out.print("Novo arquivo escrito: " + file.getKey() + "\n");
			}
		}
	}

	/**
	 * Read and write in disk the missed files from the case/death API
	 * 
	 * @throws IOException if the API fails
	 */
	public static void writeMissedData() throws IOException {
		GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		GHRepository repo = github.getUser("CSSEGISandData").getRepository("COVID-19");
		List<GHContent> directory = repo.getDirectoryContent("csse_covid_19_data/csse_covid_19_daily_reports");

		for (GHContent gitFile : directory) {
			String name = gitFile.getName();
			if (name.endsWith(".csv")) {
				File file = new File("data/" + name);
				
				// If the file exists just ignore
				if (!file.exists()) {
					// If not, request the missed files
					String content = Requests.getContent(gitFile);
					writeData(name, CsvParser.getAllContent(content));
					System.out.print("Novo arquivo escrito: " + name + "\n");
				}
			}
		}

	}


	/**
	 * Read the file and return the data
	 * 
	 * @param fileName The name of the file
	 * 
	 * @return The read file data
	 * @throws IOException in case the file does not exist
	 */
	public static String readData(String fileName) throws IOException {
		File file = new File("data/" + fileName);
		FileReader fr;
		BufferedReader br;

		// Ensures that the file will be empty
		try {
			file.createNewFile();
			fr = new FileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		br = new BufferedReader(fr);
		String line = br.readLine();
		String content = "";
		
		while (line != null && line.length() > 0) {
			content += line;
			line = br.readLine();
		}

		// Closes the opened streams
		br.close();
		fr.close();

		return content;
	}

	/**
	 * Read every cases/death data file in disk
	 * @return Map<fileName, Lines<Fields[]>>
	 */
	public static Map<String, List<String[]>> readAllData() {
		File directory = new File("data");
		String[] fileNames = directory.list();
		
		Map<String, List<String[]>> data = new TreeMap<String, List<String[]>>();
		for (String file : fileNames) {
			try {
				//System.out.println(file);
				List<String[]> content = CsvParser.getAllContent(readData(file));	
				
				String date = file.substring(6, 10) + "-" + file.substring(0, 2) + "-" + file.substring(3, 5);
				data.put(date, content);
			} catch (IOException e) {
				// Not expected to EVER happen
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
}
