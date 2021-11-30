package com.bilgeadam.movie.dao.abstracts;

import java.util.ArrayList;

public interface IEntity {
	
	ArrayList<String> readCsv();
	
	void writeCsvToDatabase(ArrayList<String> arrayList);
	
	void readAndWriteDatabse();
	
}
