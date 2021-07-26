package com.dashboard.components.scenes.newsReports;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 * Covid-related news. Embeds a Globo page w/ covid news.
 */
public class NewsReportsScene extends AnchorPane {
	@FXML
	private WebView webView;

	@FXML
    private void initialize() {
		webView.getEngine().load("https://g1.globo.com/bemestar/coronavirus/");
    }

	public NewsReportsScene() {
		FXMLUtils.loadFXML(this);
	}
}
