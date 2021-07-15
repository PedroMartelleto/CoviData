package com.dashboard.data.importer;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import javax.xml.*;

import java.util.Base64;

public class Requests {

	public static String Vaccination () {
		String content = "";
    	try {
            // Getting the API data
    		URL url = new URL("https://api.github.com/repos/owid/covid-19-data/contents/public/data/vaccinations/country_data/Brazil.csv");
            String response = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")).readLine();
            JSONObject json = new JSONObject(response);

    	} catch (Exception ioe) {
            System.out.println(ioe.getMessage());
    	}
    	
    	return content;
	}
	
	
    public static void main(String args[]) { 
    	System.out.println(Vaccination());
    }
}