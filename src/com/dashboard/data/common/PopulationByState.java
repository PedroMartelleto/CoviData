package com.dashboard.data.common;

import java.util.HashMap;
import java.util.Map;

public class PopulationByState {
	private static Map<String, Integer> population= new HashMap<String,Integer>();
	
	static {
		population.put("Sao Paulo", 46289333);
		population.put("Minas Gerais", 21292666);
		population.put("Rio de Janeiro", 17366189);
		population.put("Bahia", 14930634);
		population.put("Parana", 11516840);
		population.put("Rio Grande do Sul", 11422973);
		population.put("Pernambuco", 9616621);
		population.put("Ceara", 9187103);
		population.put("Para", 8690745);
		population.put("Santa Catarina", 7252502);
		population.put("Maranhao", 7114598);
		population.put("Goias", 7113540);
		population.put("Amazonas", 4207714);
		population.put("Espirito Santo", 4064052);
		population.put("Paraiba", 4039277);
		population.put("Rio Grande do Norte", 3534165);
		population.put("Mato Grosso", 3526220);
		population.put("Alagoas", 3351543);
		population.put("Piaui", 3281480);
		population.put("Distrito Federal", 3055149);
		population.put("Mato Grosso do Sul", 2809394);
		population.put("Sergipe", 2318822);
		population.put("Rondonia", 1796460);
		population.put("Tocantins", 1590248);
		population.put("Acre", 894470);
		population.put("Amapa", 861773);
		population.put("Roraima", 631181);
	}
	
	public static int getPopulation(String state) {
		return population.get(state);
	}

}
