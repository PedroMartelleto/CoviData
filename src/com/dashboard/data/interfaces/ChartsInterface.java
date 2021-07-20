package com.dashboard.data.interfaces;

import java.util.ArrayList;
import javafx.util.Pair;

import com.sothawo.mapjfx.Coordinate;

public interface ChartsInterface {
	
	public ArrayList< Pair<Coordinate, Integer> > GetCasesMapChart();
	public ArrayList< Pair<Coordinate, Integer> > GetDeathsMapChart();
	
	public ArrayList< Pair<Integer, Integer> > GetCasesLineChart();
	public ArrayList< Pair<Integer, Integer> > GetDeathsLineChart();
}
