package com.dashboard.data.globals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sothawo.mapjfx.Coordinate;

/**
 * Hard-coded values that are (mostly) constant over time.
 */
public class BrazilGlobals {
	/**
	 * String that represents the option to get data from all states (sum of each individual state).
	 */
	public static final String ALL_STATES = "Todos os estados";
	
	/**
	 * A coordinate in approximately the center of Brazil.
	 */
	public static final Coordinate CENTER = new Coordinate(-15.723588679352947, -46.98361582418985);
	
	private static long totalPopulation = 0;	
	private static Map<String, Long> populationsMap = new HashMap<String, Long>();
	private static ArrayList<String> sortedStateNames = new ArrayList<>();
	
	static {
		populationsMap.put("Sao Paulo", 46289333L);
		populationsMap.put("Minas Gerais", 21292666L);
		populationsMap.put("Rio de Janeiro", 17366189L);
		populationsMap.put("Bahia", 14930634L);
		populationsMap.put("Parana", 11516840L);
		populationsMap.put("Rio Grande do Sul", 11422973L);
		populationsMap.put("Pernambuco", 9616621L);
		populationsMap.put("Ceara", 9187103L);
		populationsMap.put("Para", 8690745L);
		populationsMap.put("Santa Catarina", 7252502L);
		populationsMap.put("Maranhao", 7114598L);
		populationsMap.put("Goias", 7113540L);
		populationsMap.put("Amazonas", 4207714L);
		populationsMap.put("Espirito Santo", 4064052L);
		populationsMap.put("Paraiba", 4039277L);
		populationsMap.put("Rio Grande do Norte", 3534165L);
		populationsMap.put("Mato Grosso", 3526220L);
		populationsMap.put("Alagoas", 3351543L);
		populationsMap.put("Piaui", 3281480L);
		populationsMap.put("Distrito Federal", 3055149L);
		populationsMap.put("Mato Grosso do Sul", 2809394L);
		populationsMap.put("Sergipe", 2318822L);
		populationsMap.put("Rondonia", 1796460L);
		populationsMap.put("Tocantins", 1590248L);
		populationsMap.put("Acre", 894470L);
		populationsMap.put("Amapa", 861773L);
		populationsMap.put("Roraima", 631181L);
		
		for (long value : populationsMap.values()) {
			totalPopulation += value;
		}
				
		sortedStateNames.addAll(populationsMap.keySet());
		Collections.sort(sortedStateNames);
		
		populationsMap.put(ALL_STATES, totalPopulation);
		sortedStateNames.add(0, ALL_STATES);
	}
	
	/**
	 * Returns the total population in Brazil.
	 * @return
	 */
	public static long getBrazilPopulation() {
		return totalPopulation;
	}
	
	/**
	 * Returns a list of state names in Brazil.
	 * @return
	 */
	public static ArrayList<String> getStateNames() {
		return sortedStateNames;
	}
	
	/**
	 * Gets the population of a state by that state's name. Also supports getting the population of the entire country.
	 * @param state name of the state from which to get the population.
	 * @return
	 */
	public static long getPopulation(String state) {
		return populationsMap.get(state);
	}
}
