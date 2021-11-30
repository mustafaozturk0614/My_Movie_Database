package com.bilgeadam.movie.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
	
	public static Statement createStatement() {
		
		Connection connection = DatabaseConnection.getInstance().getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		return statement;
	}
	
	public static PreparedStatement createPreparedStatement(String select) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(select);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return statement;
	}
	
}
