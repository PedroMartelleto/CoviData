package com.dashboard.data.importer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;


public class Requests {
	Date today = new Date();
	
	public String modeloGit() {
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getUser("CSSEGISandData").getRepository("COVID-19");
			GHContent content = repo.getFileContent("csse_covid_19_data/csse_covid_19_daily_reports/01-01-2021.csv");
			return new String(content.read().readAllBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getLastDailyReport() {
		long day = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		
		String formatedDate = format.format(new Date(day)); 
		boolean notRead = true;
		
		String retorno = null;
		
		while(notRead) {
			try {
				GitHub github = new GitHubBuilder().withOAuthToken("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").build();
				GHRepository repo = github.getUser("CSSEGISandData").getRepository("COVID-19");
				GHContent content = repo.getFileContent("csse_covid_19_data/csse_covid_19_daily_reports/" + formatedDate + ".csv");
				notRead = false;
				retorno = new String(content.read().readAllBytes());
			} catch (IOException e) {
				day -= 24*60*60*1000;
				formatedDate = format.format(new Date(day)); 
				e.printStackTrace();
			}
		}
		
		return retorno;
	}
	
	
	public static String getContent(GHContent gitContent) throws IOException {
		return new String(gitContent.read().readAllBytes());
	}

	
	public static Map<String, String> getAllCases() {
		Map<String, String> contents = new HashMap<String, String>();
		
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getUser("CSSEGISandData").getRepository("COVID-19");
			List<GHContent> gitContent = repo.getDirectoryContent("csse_covid_19_data/csse_covid_19_daily_reports");

			for (GHContent file : gitContent) {
				String name = file.getName();
				if (name.endsWith(".csv")) {
					contents.put(name, getContent(file));
					System.out.print("Arquivo lido: " + name +"\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contents;
	}
	
	
	public static String getTotalVaccinated() {
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getUser("owid").getRepository("covid-19-data");
			GHContent content = repo.getFileContent("public/data/vaccinations/country_data/Brazil.csv");
			return new String(content.read().readAllBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	
}