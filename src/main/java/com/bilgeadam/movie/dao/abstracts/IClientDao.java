package com.bilgeadam.movie.dao.abstracts;

public interface IClientDao {
	void createDatabase();
	
	void deleteDatabase();
	
	// void queryByMovieGenre(String genre);
	
	String queryRating();
	
	String querybyName();
	
	void queryByYear();
	
	void queryByMovieGenre();
	
}
