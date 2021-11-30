package com.bilgeadam.movie.business;

import java.util.TreeMap;

import com.bilgeadam.movie.dto.NameTsv;

public class DataImport implements Runnable {
	TreeMap<String, NameTsv> nameMap = new TreeMap<>();
	
	@Override
	public void run() {
		ClientHandler.initializingStarted();
		MovieBusinnes.getInstance().importData();
		
		NameBusines.getInstance().importData();
		
		ClientHandler.initializingStopped();
	}
	
}
