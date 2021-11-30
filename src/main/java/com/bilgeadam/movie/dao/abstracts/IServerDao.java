package com.bilgeadam.movie.dao.abstracts;

import java.util.ArrayList;

public interface IServerDao {
	
	String findName(String name);
	
	// ArrayList<String> findName(String name, TreeMap<String, NameTsv> names);
	//
	// void findMovieForName(ArrayList<String> knowfortitles, TreeMap<String,
	// MovieTsv> movies);
	
	String findMovieForName(ArrayList<String> knowfortitles);
	
	String findMovie(ArrayList<String> knowForTitles);
	
	ArrayList<String> findKnowForTlitles(String name);
	
}
