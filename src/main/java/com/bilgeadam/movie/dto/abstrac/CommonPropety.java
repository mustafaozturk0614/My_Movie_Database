package com.bilgeadam.movie.dto.abstrac;

public abstract class CommonPropety {
	int id;
	
	public CommonPropety(int id) {
		super();
		this.id = id;
	}
	
	public CommonPropety() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
