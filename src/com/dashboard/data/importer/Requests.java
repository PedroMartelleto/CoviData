package com.dashboard.data.importer;

import java.io.IOException;
import java.util.List;
import java.util.Date;

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
	
	public void testeFile() {
    	Date lastSaved = new Date();
    	long today = System.currentTimeMillis();
    	int n=0;
    	
    	long limite = System.currentTimeMillis();
    	for (int i=0; i<10; i++) limite -= 24*60*60*1000;
    	
    	
    	while (today > limite) {
    		lastSaved = new Date(today);
    		today -= 24*60*60*1000;
    		n++;
    	}
    	
    	for (int i=0; i<n; i++) {
    		// Salva arquivo do github
    		today += 24*60*60*1000;
    	}
	}
}