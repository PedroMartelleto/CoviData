package com.dashboard.data.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
 
public class Requests {

	public static String Vaccination () {
		String content = "";
    	try {
            // Getting the API data
    		URL url = new URL("https://api.github.com/repos/owid/covid-19-data/contents/public/data/vaccinations/country_data/Brazil.csv");
            String response = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")).readLine();
            
            // Selecting the desired field
            content = response.split(",")[9].split(":")[1];
            content = content.substring(10, content.length() - 1);
            System.out.println(content);
            
            // Decoding from base64
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = content.getBytes();
            bytes = decoder.decode(bytes);
            content = new String(bytes);
            
    	} catch (IOException ioe) {
            System.out.println(ioe.getMessage());
    	}
    	
    	return content;
	}
	
	
    public static void main(String args[]) { 
    	System.out.println(Vaccination());
    }
}