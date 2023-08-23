package com.project.cinemax.util;

import java.util.ArrayList;
import java.util.HashMap;


public class SelectedSeatsSingleton {
	
	private HashMap<Integer, ArrayList<Integer>> map;
	
	private static SelectedSeatsSingleton instance;

    private SelectedSeatsSingleton() {
        // Private constructor prevents external instantiation
    	this.map = new HashMap<>();
    }

    public static SelectedSeatsSingleton getInstance() {
        if (instance == null) {
            instance = new SelectedSeatsSingleton();
        }
        return instance;
    }

	public HashMap<Integer, ArrayList<Integer>> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, ArrayList<Integer>> map) {
		this.map = map;
	}
    
    
}
