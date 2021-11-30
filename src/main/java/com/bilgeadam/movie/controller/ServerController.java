package com.bilgeadam.movie.controller;

import java.util.ArrayList;

import com.bilgeadam.movie.dao.abstracts.IServerDao;
import com.bilgeadam.movie.dto.Client;

public class ServerController implements IServerDao {
	IServerDao iServerDao;
	Client client;
	
	public ServerController(IServerDao iServerDao) {
		super();
		this.iServerDao = iServerDao;
		this.client = new Client();
	}
	
	@Override
	public String findName(String name) {
		
		return this.iServerDao.findName(name);
	}
	
	@Override
	public String findMovieForName(ArrayList<String> knowfortitles) {
		return this.iServerDao.findMovieForName(knowfortitles);
	}
	
	@Override
	public String findMovie(ArrayList<String> knowForTitles) {
		
		return this.iServerDao.findMovie(knowForTitles);
	}
	
	@Override
	public ArrayList<String> findKnowForTlitles(String name) {
		return this.iServerDao.findKnowForTlitles(name);
	}
	
}