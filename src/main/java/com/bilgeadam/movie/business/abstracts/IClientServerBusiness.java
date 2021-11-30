package com.bilgeadam.movie.business.abstracts;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.Socket;

import com.bilgeadam.movie.dto.NameTsv;

public interface IClientServerBusiness<T> {
	String getRecivedMessage(Socket socket);
	
	void sendToMessage(Socket socket, String string);
	
	boolean nameDataExisting();
	
	void deserializeNames();
	
	void serializeData(T t);
	
	boolean movieDataExisting();
	
	void deserializeMovies();
	
	void readMovies();
	
	// void serializeMovies();
	
	public void addName(T t);
	
	public void addMovie(T t);
	
	void parse(String line, T t);
	
	void writeExternal(ObjectOutput out, T t);
	
	void readExternal(ObjectInput in, T t) throws IOException, ClassNotFoundException;
	
	void readNames(NameTsv name);
}
