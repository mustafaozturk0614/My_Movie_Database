package com.bilgeadam.movie.dto.entitiy;

import java.sql.Date;

import com.bilgeadam.movie.dto.abstrac.CommonPropety;

public class Rating extends CommonPropety {
	int rating;
	Date createdDate;
	User userDto;
	
	public Rating() {
		
	}
	
	public Rating(int rating, Date createdDate, User userDto) {
		super();
		this.rating = rating;
		this.createdDate = createdDate;
		this.userDto = userDto;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public User getUserDto() {
		return userDto;
	}
	
	public void setUserDto(User userDto) {
		this.userDto = userDto;
	}
	
}
