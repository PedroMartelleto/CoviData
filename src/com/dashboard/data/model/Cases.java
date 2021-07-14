package com.dashboard.data.model;

public class Cases {
	// Variables (setting only in the constructor)
	private String state;
	public String getState() {
		return this.state;
	}

	private int confirmed;
	public int getConfirmed() {
		return this.confirmed;
	}
	
	private int deaths;
	public int getDeaths() {
		return this.deaths;
	}
	
	private int recovered;
	public int getRecovered() {
		return this.recovered;
	}
	
	private int active;
	public int getActive() {
		return this.active;
	}
	
	private int vaccinated;
	public int getVaccinated() {
		return this.vaccinated;
	}
	
	public Cases (String state, int confirmed, int deaths, int recovered, int active, int vaccinated) {
		this.state = state;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.active = active;
		this.vaccinated = vaccinated;
	}
}
