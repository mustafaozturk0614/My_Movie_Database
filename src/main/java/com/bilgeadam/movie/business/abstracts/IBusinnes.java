package com.bilgeadam.movie.business.abstracts;

import java.util.ArrayList;

public interface IBusinnes {
	static void placingReadData() {
		
	}
	
	void serializeTsv();
	
	void readAndWriteTsv();
	
	ArrayList<String> readLine(ArrayList<String> arrayList, String path);
	
	void CreateTsvDatase();
}
