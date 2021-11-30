package com.bilgeadam.movie.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static DatabaseConnection instance;
	private static DatabaseInformation databaseInformation;
	private Connection connection;
	private String url = databaseInformation.getUrl();
	private String userName = databaseInformation.getUserName();
	private String password = databaseInformation.getPassword();
	
	static {
		databaseInformation = new DatabaseInformation();
		
	}
	
	private DatabaseConnection() {
		try {
			Class.forName(databaseInformation.getForNameData());
			System.out.println("postgresql jar driver yüklendi");
			this.connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Bağlantı başarılı");
		} catch (Exception e) {
			System.out.println("hatalar meydana geldi");
			System.out.println(e.getMessage());
			
		}
	}
	
	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
			
		} else {
			try {
				if (instance.getConnection().isClosed()) {
					instance = new DatabaseConnection();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return instance;
	}
	
	public Connection getConnection() {
		
		return connection;
	}
	
}
