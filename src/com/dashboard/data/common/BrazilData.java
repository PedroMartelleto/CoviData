package com.dashboard.data.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrazilData {
	public static final String ALL_STATES = "Todos os estados";
	
	private static long totalPopulation = 0;	
	private static Map<String, Long> populationsMap = new HashMap<String, Long>();
	private static ArrayList<String> sortedStateNames = new ArrayList<>();
	
	static {
		populationsMap.put("São Paulo", 46289333L);
		populationsMap.put("Minas Gerais", 21292666L);
		populationsMap.put("Rio de Janeiro", 17366189L);
		populationsMap.put("Bahia", 14930634L);
		populationsMap.put("Paraná", 11516840L);
		populationsMap.put("Rio Grande do Sul", 11422973L);
		populationsMap.put("Pernambuco", 9616621L);
		populationsMap.put("Ceará", 9187103L);
		populationsMap.put("Pará", 8690745L);
		populationsMap.put("Santa Catarina", 7252502L);
		populationsMap.put("Maranhão", 7114598L);
		populationsMap.put("Goiás", 7113540L);
		populationsMap.put("Amazonas", 4207714L);
		populationsMap.put("Espírito Santo", 4064052L);
		populationsMap.put("Paraíba", 4039277L);
		populationsMap.put("Rio Grande do Norte", 3534165L);
		populationsMap.put("Mato Grosso", 3526220L);
		populationsMap.put("Alagoas", 3351543L);
		populationsMap.put("Piauí", 3281480L);
		populationsMap.put("Distrito Federal", 3055149L);
		populationsMap.put("Mato Grosso do Sul", 2809394L);
		populationsMap.put("Sergipe", 2318822L);
		populationsMap.put("Rondônia", 1796460L);
		populationsMap.put("Tocantins", 1590248L);
		populationsMap.put("Acre", 894470L);
		populationsMap.put("Amapá", 861773L);
		populationsMap.put("Roraima", 631181L);
		
		for (long value : populationsMap.values()) {
			totalPopulation += value;
		}
				
		sortedStateNames.addAll(populationsMap.keySet());
		Collections.sort(sortedStateNames);
		
		populationsMap.put(ALL_STATES, totalPopulation);
		sortedStateNames.add(0, ALL_STATES);
	}
	
	public static long getBrazilPopulation() {
		return totalPopulation;
	}
	
	public static ArrayList<String> getStateNames() {
		return sortedStateNames;
	}
	
	/**
	 * Gets the population of a state by that state's name. Also getting the entire Brazil population.
	 * @param state
	 * @return
	 */
	public static long getPopulation(String state) {
		return populationsMap.get(state);
	}
}
