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

public class LinkDao implements IEntity {
	@Override
	public ArrayList<String> readCsv() {
		
		ArrayList<String> arrayList = new ArrayList<String>();
		String tokenString = "\n"; //$NON-NLS-1$
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FileInformation.CSV_PATH_lİNKS))) {
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
		
	}
	
	@Override
	public void writeCsvToDatabase(ArrayList<String> arrayList) {
		int count = 0;
		String tokenString = ",|"; //$NON-NLS-1$
		ArrayList<String> templist = new ArrayList<String>();
		for (int i = 0; i < arrayList.size(); i++) {
			count++;
			templist.clear();
			String string = arrayList.get(i).replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
			templist = BAUtils.createToken(string, tokenString, templist);
			PreparedStatement preparedStatement;
			
			try {
				String sql = "insert into links(movie_id,imdb_id,tmdb_id) values(?,?,?)"; //$NON-NLS-1$
				
				preparedStatement = DatabaseUtils.createPreparedStatement(sql);
				for (int j = 0; j < templist.size(); j++) {
					
					Long id = Long.parseLong(templist.get(j));
					
					preparedStatement.setLong(j + 1, id);
					
				}
				
				int rowEffected = preparedStatement.executeUpdate();
				
				if (rowEffected > 0) {
					System.out.println(count + Messages.getString("ClientBusiness.26")); //$NON-NLS-1$
				} else {
					System.out.println(Messages.getString("ClientBusiness.13")); //$NON-NLS-1$
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage() + Messages.getString("ClientBusiness.16")); //$NON-NLS-1$
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void readAndWriteDatabse() {
		
		writeCsvToDatabase(readCsv());
		
	}
	
	public static void main(String[] args) {
		LinkDao linkDao = new LinkDao();
		linkDao.readAndWriteDatabse();
	}
	
}
