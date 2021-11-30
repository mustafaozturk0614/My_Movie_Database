package com.bilgeadam.movie.utils;

public enum ETsvDatabaseInformation {
	MOVİES(1, "movies.tsv"), NAMES(2, "names.tsv");
	
	private final int id;
	private final String tabloAdi;
	
	ETsvDatabaseInformation(int id, String string) {
		this.id = id;
		this.tabloAdi = string;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getTabloAdi() {
		return tabloAdi;
	}
}
