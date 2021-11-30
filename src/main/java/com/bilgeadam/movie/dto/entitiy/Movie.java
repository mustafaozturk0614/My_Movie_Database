package com.bilgeadam.movie.dto.entitiy;

import java.util.HashMap;

import com.bilgeadam.movie.dto.abstrac.CommonPropety;

public class Movie extends CommonPropety {
	String title;
	int genre_id;
	HashMap<Integer, String> genres;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public Movie(String title, int genre_id, HashMap<Integer, String> genres) {
		super();
		this.title = title;
		this.genre_id = genre_id;
		this.genres = genres;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getGenre_id() {
		return genre_id;
	}
	
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	public HashMap<Integer, String> getGenres() {
		return genres;
	}
	
	public void setGenres(HashMap<Integer, String> genres) {
		this.genres = genres;
	}
	
	@Override
	public String toString() {
		return "Movie [title=" + title + ", genre_id=" + genre_id + ", genres=" + genres + "]";
	}
	
}
