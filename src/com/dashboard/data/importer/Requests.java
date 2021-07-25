package com.dashboard.data.importer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class Requests {
	private static Map<String, String> csvCache = new HashMap<String, String>();
	
	/**
	 * Gets the content from a GitGub API file
	 * 
	 * @param gitContent The GitHub API file content
	 * 
	 * @return The git file content
	 * @throws IOException if the API send the data in a different format
	 */
	public static String getContent(GHContent gitContent) throws IOException {
		return new String(gitContent.read().readAllBytes());
	}

	/**
	 * Gets every file and its contents from the cases/deaths API
	 * 
	 * @return Map<Name, Content>, both in string format
	 */
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
					System.out.print("Arquivo lido: " + name + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contents;
	}

	public static String getReportByDay(long day) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		String formatedDate = format.format(new Date(day));
		String retorno = null;
		
		// TODO: Read local if the file exists. If the file is downloaded, write to disk. (1)
		
		if (csvCache.get(formatedDate) != null) {
			return csvCache.get(formatedDate);
		}
		
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getUser("CSSEGISandData").getRepository("COVID-19");
			GHContent content = repo.getFileContent("csse_covid_19_data/csse_covid_19_daily_reports/" + formatedDate + ".csv");
			retorno = new String(content.read().readAllBytes());
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
		
		csvCache.put(formatedDate, retorno);
	
		return retorno;
	}
	
	/**
	 * Gets the content of the vaccination API
	 * 
	 * @return The content in a string format
	 */
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