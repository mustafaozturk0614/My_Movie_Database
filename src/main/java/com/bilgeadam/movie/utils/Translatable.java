package com.bilgeadam.movie.utils;

public interface Translatable {
	
	String getI18NKey();
	
	default String getI18NKeyIdentifier() {
		return this.getClass().getSimpleName().toUpperCase() + ".";
	}
}
