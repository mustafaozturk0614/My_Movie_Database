package com.bilgeadam.movie.dto.entitiy;

import java.sql.Date;

import com.bilgeadam.movie.dto.abstrac.CommonPropety;

public class Link extends CommonPropety {
	String tag;
	Movie movieDto;
	Date createdDate;
	int imdbId;
	int tmdblId;
	
	public Link() {
		
	}
	
	public Link(String tag, Movie movie, Date createdDate, int imdbId, int tmdblId) {
		super();
		this.tag = tag;
		this.movieDto = movie;
		this.createdDate = createdDate;
		this.imdbId = imdbId;
		this.tmdblId = tmdblId;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Movie getMovieDto() {
		return movieDto;
	}
	
	public void setMovieDto(Movie movieDto) {
		this.movieDto = movieDto;
	}
	
	public int getImdbId() {
		return imdbId;
	}
	
	public void setImdbId(int imdbId) {
		this.imdbId = imdbId;
	}
	
	public int getTmdblId() {
		return tmdblId;
	}
	
	public void setTmdblId(int tmdblId) {
		this.tmdblId = tmdblId;
	}
	
}
