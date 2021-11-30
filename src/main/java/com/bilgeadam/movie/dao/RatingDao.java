package com.bilgeadam.movie.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bilgeadam.movie.business.Messages;
import com.bilgeadam.movie.dao.abstracts.IEntity;
import com.bilgeadam.movie.utils.BAUtils;
import com.bilgeadam.movie.utils.DatabaseUtils;
import com.bilgeadam.movie.utils.FileInformation;

public class RatingDao implements IEntity {
	@Override
	public ArrayList<String> readCsv() {
		
		ArrayList<String> arrayList = new ArrayList<String>();
		String tokenString = "\n";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FileInformation.CSV_PATH_RATİNGS))) {
			while (true) {
				String lineString = bufferedReader.readLine();
				if (lineString == null) {
					break;
				}
				arrayList = BAUtils.createToken(lineString, tokenString, arrayList);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		arrayList.remove(0);
		return arrayList;
		// writeCsvToDatabase(arrayList, tableName);
		
	}
	
	@Override
	public void writeCsvToDatabase(ArrayList<String> arrayList) {
		int count = 0;
		String tokenString = ",|";
		ArrayList<String> templist = new ArrayList<String>();
		for (int i = 0; i < arrayList.size(); i++) {
			count++;
			templist.clear();
			String string = arrayList.get(i).replace("\"", "");
			templist = BAUtils.createToken(string, tokenString, templist);
			PreparedStatement preparedStatement;
			
			try {
				String sql = "insert into ratings(user_id,movie_id,rating,create_date) values(?,?,?,?)";
				
				preparedStatement = DatabaseUtils.createPreparedStatement(sql);
				
				long id = Long.parseLong(templist.get(0));
				long movie_id = Long.parseLong(templist.get(1));
				double rating = Double.parseDouble(templist.get(2));
				Long time = Long.parseLong(templist.get(3));
				
				preparedStatement.setLong(1, id);
				preparedStatement.setLong(2, movie_id);
				preparedStatement.setDouble(3, rating);
				preparedStatement.setLong(4, time);
				
				int rowEffected = preparedStatement.executeUpdate();
				
				if (rowEffected > 0) {
					System.out.println(count + Messages.getString("ClientBusiness.27"));
				} else {
					System.out.println(Messages.getString("ClientBusiness.13"));
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage() + Messages.getString("ClientBusiness.16"));
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void readAndWriteDatabse() {
		
		writeCsvToDatabase(readCsv());
		
	}
	
}
