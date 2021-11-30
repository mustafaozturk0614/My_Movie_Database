package com.bilgeadam.movie.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.bilgeadam.movie.business.Messages;
import com.bilgeadam.movie.dao.abstracts.IEntity;
import com.bilgeadam.movie.utils.DatabaseUtils;
import com.bilgeadam.movie.utils.ECsvDatabaseInformation;
import com.bilgeadam.movie.utils.FileInformation;

public class MovieDao implements IEntity {
	
	@Override
	public ArrayList<String> readCsv() {
		ArrayList<String> arrayList = new ArrayList<String>();
		String path = FileInformation.CSV_PATH + "\\" + ECsvDatabaseInformation.MOVİES.getTabloAdi() + ".csv"; //$NON-NLS-1$ //$NON-NLS-2$
		
		String emptyString = ""; //$NON-NLS-1$
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			while (true) {
				String lineString = bufferedReader.readLine();
				if (lineString == null) {
					break;
				}
				StringTokenizer tokenizer = new StringTokenizer(lineString, "\n"); //$NON-NLS-1$
				
				while (tokenizer.hasMoreElements()) {
					
					emptyString = (String) tokenizer.nextToken();
					
					arrayList.add(emptyString);
					
				}
				
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
		ArrayList<String> templist = new ArrayList<String>();
		for (int i = 0; i < arrayList.size(); i++) {
			count++;
			String string = arrayList.get(i).replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
			StringTokenizer tokenizer1 = new StringTokenizer(string, ",|"); //$NON-NLS-1$
			templist.clear();
			while (tokenizer1.hasMoreElements()) {
				String empty = ""; //$NON-NLS-1$
				empty = (String) tokenizer1.nextToken();
				templist.add(empty);
				
			}
			PreparedStatement preparedStatement;
			PreparedStatement preparedStatement1;
			try {
				String sql = "insert into movies(movie_id,title,genres_id) values(?,?,?)"; //$NON-NLS-1$
				String sql1 = "insert into genres(genres_id,genres1,genres2,genres3,genres4,genres5,genres6,genres7,movie_id)values(?,?,?,?,?,?,?,?,?)"; //$NON-NLS-1$
				
				preparedStatement = DatabaseUtils.createPreparedStatement(sql);
				preparedStatement1 = DatabaseUtils.createPreparedStatement(sql1);
				String s = templist.get(0);
				s.replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
				long id = Long.parseLong(s);
				
				preparedStatement.setLong(1, id);
				preparedStatement.setString(2, templist.get(1));
				// preparedStatement.setString(2, templist.get(1));
				preparedStatement1.setLong(9, id);
				preparedStatement1.setLong(1, id);
				
				preparedStatement.setLong(3, id);
				
				for (int k = 2; k < templist.size(); k++) {
					int size = templist.size();
					int a = 9 - size;
					if (a > 0) {
						while (a > 0) {
							
							preparedStatement1.setString((9 - a), null);
							a--;
						}
					}
					
					preparedStatement1.setString(k, templist.get(k));
					
				}
				
				int rowEffected = preparedStatement.executeUpdate();
				
				if (rowEffected > 0) {
					System.out.println(count + Messages.getString("ClientBusiness.12")); //$NON-NLS-1$
				} else {
					System.out.println(Messages.getString("ClientBusiness.13")); //$NON-NLS-1$
				}
				int row = preparedStatement1.executeUpdate();
				if (row > 0) {
					System.out.println(count + Messages.getString("ClientBusiness.14")); //$NON-NLS-1$
				} else {
					System.out.println(Messages.getString("ClientBusiness.15")); //$NON-NLS-1$
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
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
	
}
