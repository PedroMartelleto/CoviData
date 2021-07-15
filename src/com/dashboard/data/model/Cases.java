package com.dashboard.data.model;

public class Cases {
	// Variables (setting only in the constructor)
	private final String state;
	public String getState() {
		return this.state;
	}

	private final int confirmed;
	public int getConfirmed() {
		return this.confirmed;
	}
	
	private final int deaths;
	public int getDeaths() {
		return this.deaths;
	}
	
	private final int recovered;
	public int getRecovered() {
		return this.recovered;
	}
	
	private final int active;
	public int getActive() {
		return this.active;
	}
	
	private final int vaccinated;
	public int getVaccinated() {
		return this.vaccinated;
	}
	
	public Cases(String state, int confirmed, int deaths, int recovered, int active, int vaccinated) {
		this.state = state;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.active = active;
		this.vaccinated = vaccinated;
	}
}
