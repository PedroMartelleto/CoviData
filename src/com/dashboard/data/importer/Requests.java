package com.dashboard.data.importer;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class Requests {
	public static String vaccination() {
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getUser("CSSEGISandData").getRepository("COVID-19");
			repo.getDirectoryContent("csse_covid_19_data/csse_covid_19_daily_reports");
			List<GHContent> content = repo.getDirectoryContent("csse_covid_19_data/csse_covid_19_daily_reports");
			content.
			content.
			return new String();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}