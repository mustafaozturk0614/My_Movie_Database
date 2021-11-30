package com.bilgeadam.movie.utils;

public enum ECsvDatabaseInformation {
	
	RATİNGS(1, "ratings"), LİNKS(2, "links"), TAGS(3, "tags"), GENRES(4, "genres"), MOVİES(5, "movies");
	
	private final int id;
	private final String tabloAdi;
	
	ECsvDatabaseInformation(int id, String string) {
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
