package com.bilgeadam.movie.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.bilgeadam.movie.business.Messages;
import com.bilgeadam.movie.dao.abstracts.IClientDao;
import com.bilgeadam.movie.dto.Client;
import com.bilgeadam.movie.utils.BAUtils;

public class ClientController implements IClientDao {
	IClientDao ıcClientDao;
	Client client;
	
	public ClientController(IClientDao icClientDao) {
		super();
		this.ıcClientDao = icClientDao;
		this.client = new Client();
	}
	
	@Override
	public void createDatabase() {
		
		try {
			String line = BAUtils.isDatabaseExist();
			
			if (!line.equalsIgnoreCase("1")) {
				this.ıcClientDao.createDatabase();
			} else {
				System.out.println(Messages.getString("ClientBusiness.32")); //$NON-NLS-1$
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println(Messages.getString("ClientBusiness.34"));
			this.ıcClientDao.deleteDatabase();
		}
		
	}
	
	@Override
	public void queryByYear() {
		this.ıcClientDao.queryByYear();
		
	}
	
	@Override
	public String queryRating() {
		if (this.ıcClientDao.queryRating() == null) {
			System.out.println(Messages.getString("ClientBusiness.29")); //$NON-NLS-1$
			return null;
		} else {
			return null;
		}
		
	}
	
	@Override
	public String querybyName() {
		return ıcClientDao.querybyName();
		
	}
	
	public IClientDao getIcClientDao() {
		return ıcClientDao;
	}
	
	public void setIcClientDao(IClientDao icClientDao) {
		ıcClientDao = icClientDao;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public void queryByMovieGenre() {
		this.ıcClientDao.queryByMovieGenre();
		
	}
	
	@Override
	public void deleteDatabase() {
		BufferedReader bufferedReader = null;
		
		try {
			String line = BAUtils.isDatabaseExist();
			if (line.equalsIgnoreCase("1") && line == null) {
				this.ıcClientDao.deleteDatabase();
			} else {
				System.out.println(Messages.getString("ClientBusiness.33"));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(Messages.getString("ClientBusiness.34"));
			this.ıcClientDao.deleteDatabase();
		}
		
	}
	
}
