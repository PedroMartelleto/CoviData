package com.dashboard.data.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrazilStates {
	private static Map<String, Integer> populationsMap = new HashMap<String,Integer>();
	private static ArrayList<String> sortedStateNames = new ArrayList<>();
	
	static {
		populationsMap.put("São Paulo", 46289333);
		populationsMap.put("Minas Gerais", 21292666);
		populationsMap.put("Rio de Janeiro", 17366189);
		populationsMap.put("Bahia", 14930634);
		populationsMap.put("Parana", 11516840);
		populationsMap.put("Rio Grande do Sul", 11422973);
		populationsMap.put("Pernambuco", 9616621);
		populationsMap.put("Ceara", 9187103);
		populationsMap.put("Para", 8690745);
		populationsMap.put("Santa Catarina", 7252502);
		populationsMap.put("Maranhao", 7114598);
		populationsMap.put("Goias", 7113540);
		populationsMap.put("Amazonas", 4207714);
		populationsMap.put("Espirito Santo", 4064052);
		populationsMap.put("Paraiba", 4039277);
		populationsMap.put("Rio Grande do Norte", 3534165);
		populationsMap.put("Mato Grosso", 3526220);
		populationsMap.put("Alagoas", 3351543);
		populationsMap.put("Piaui", 3281480);
		populationsMap.put("Distrito Federal", 3055149);
		populationsMap.put("Mato Grosso do Sul", 2809394);
		populationsMap.put("Sergipe", 2318822);
		populationsMap.put("Rondonia", 1796460);
		populationsMap.put("Tocantins", 1590248);
		populationsMap.put("Acre", 894470);
		populationsMap.put("Amapa", 861773);
		populationsMap.put("Roraima", 631181);
		
		sortedStateNames.addAll(populationsMap.keySet());
		Collections.sort(sortedStateNames);
	}
	
	public static ArrayList<String> getStateNames() {
		return sortedStateNames;
	}
	
	public static int getPopulationOfState(String state) {
		return populationsMap.get(state);
	}

}
