package com.bilgeadam.movie.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

import com.bilgeadam.movie.business.Messages;
import com.bilgeadam.movie.dao.abstracts.IClientDao;
import com.bilgeadam.movie.dao.abstracts.IEntity;
import com.bilgeadam.movie.utils.BAUtils;
import com.bilgeadam.movie.utils.DatabaseConnection;
import com.bilgeadam.movie.utils.DatabaseUtils;
import com.bilgeadam.movie.utils.ECsvDatabaseInformation;
import com.bilgeadam.movie.utils.EGenre;
import com.bilgeadam.movie.utils.FileInformation;

public class ClientDao implements IClientDao {
	IEntity[] entities = new IEntity[4];
	Scanner scanner = new Scanner(System.in);
	
	public ClientDao() {
		this.entities[0] = new MovieDao();
		this.entities[1] = new LinkDao();
		this.entities[2] = new RatingDao();
		this.entities[3] = new TagDao();
	}
	
	@Override
	public void createDatabase() {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(FileInformation.DATABASE_CONTROL));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			System.out.println(LocalDateTime.now() + Messages.getString("ClientBusiness.17")); //$NON-NLS-1$
			this.entities[0].readAndWriteDatabse();
			this.entities[1].readAndWriteDatabse();
			this.entities[2].readAndWriteDatabse();
			this.entities[3].readAndWriteDatabse();
			bufferedWriter.write("1");
			bufferedWriter.flush();
			System.out.println("\n" + LocalDateTime.now() + Messages.getString("ClientBusiness.11")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			try {
				bufferedWriter.write(0);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
	
	@Override
	
	public void deleteDatabase() {
		BufferedWriter bufferedWriter = null;
		System.out.println(LocalDateTime.now() + Messages.getString("ClientBusiness.18")); //$NON-NLS-1$
		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = null;
		String query = ""; //$NON-NLS-1$
		for (ECsvDatabaseInformation string : ECsvDatabaseInformation.values()) {
			query = "delete from "; //$NON-NLS-1$
			query = query + " " + string.getTabloAdi(); //$NON-NLS-1$
			statement = DatabaseUtils.createPreparedStatement(query);
			int a;
			try {
				
				bufferedWriter = new BufferedWriter(new FileWriter(FileInformation.DATABASE_CONTROL));
				a = statement.executeUpdate();
				System.out.printf(Messages.getString("ClientBusiness.19"), a, string.getTabloAdi()); //$NON-NLS-1$
				bufferedWriter.write("0");
				bufferedWriter.flush();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		System.out.println("\n" + LocalDateTime.now() + Messages.getString("ClientBusiness.20")); //$NON-NLS-1$ //$NON-NLS-2$
		
	}
	
	@Override
	public void queryByYear() {
		
		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = null;
		
		int year = BAUtils.readInt(Messages.getString("ClientBusiness.21"));
		
		System.out.println("\n\n***" + year + "***"); //$NON-NLS-1$ //$NON-NLS-2$
		
		for (int i = 1; i < 6; i++) {
			
			if (i == 1) {
				System.out.println("---------------------"); //$NON-NLS-1$
				System.out.println("===Adventure==="); //$NON-NLS-1$
				String query = "select * from movies as m JOIN genres as g   on m.movie_id = g.movie_id " //$NON-NLS-1$
						+ "where m.title like('%" + year + "%') and  genres1='Adventure' order by m.movie_id limit 10"; //$NON-NLS-1$ //$NON-NLS-2$
				statement = DatabaseUtils.createPreparedStatement(query);
				System.out.println("---------------------"); //$NON-NLS-1$
				
			}
			if (i == 2) {
				System.out.println("---------------------"); //$NON-NLS-1$
				System.out.println("===Animation==="); //$NON-NLS-1$
				String query = "select * from movies as m JOIN genres as g   on m.movie_id = g.movie_id " //$NON-NLS-1$
						+ "where m.title like('%" + year + "%') and  genres2='Animation' order by m.movie_id limit 10"; //$NON-NLS-1$ //$NON-NLS-2$
				statement = DatabaseUtils.createPreparedStatement(query);
				System.out.println("---------------------"); //$NON-NLS-1$
			}
			if (i == 3) {
				System.out.println("---------------------"); //$NON-NLS-1$
				System.out.println("===Comedy==="); //$NON-NLS-1$
				String query = "select * from movies as m JOIN genres as g   on m.movie_id = g.movie_id " //$NON-NLS-1$
						+ "where m.title like('%" + year + "%') and  genres3='Comedy' order by m.movie_id limit 10"; //$NON-NLS-1$ //$NON-NLS-2$
				statement = DatabaseUtils.createPreparedStatement(query);
				System.out.println("---------------------"); //$NON-NLS-1$
			}
			if (i == 4) {
				System.out.println("---------------------"); //$NON-NLS-1$
				System.out.println("===Romance==="); //$NON-NLS-1$
				String query = "select * from movies as m JOIN genres as g   on m.movie_id = g.movie_id " //$NON-NLS-1$
						+ "where m.title like('%" + year + "%') and genres4='Romance' order by m.movie_id limit 10"; //$NON-NLS-1$ //$NON-NLS-2$
				statement = DatabaseUtils.createPreparedStatement(query);
				System.out.println("---------------------"); //$NON-NLS-1$
			}
			if (i == 5) {
				System.out.println("---------------------"); //$NON-NLS-1$
				System.out.println("===Sci-Fi==="); //$NON-NLS-1$
				String query = "select * from movies as m JOIN genres as g   on m.movie_id = g.movie_id " //$NON-NLS-1$
						+ "where m.title like('%" + year + "%') and  genres5='Sci-Fi' order by m.movie_id limit 10"; //$NON-NLS-1$ //$NON-NLS-2$
				statement = DatabaseUtils.createPreparedStatement(query);
				
				System.out.println("---------------------"); //$NON-NLS-1$
			}
			
			try {
				ResultSet resultSet = statement.executeQuery();
				if (!resultSet.next()) {
					System.err.println(Messages.getString("ClientBusiness.22")); //$NON-NLS-1$
				}
				while (resultSet.next()) {
					
					String title = resultSet.getString(2);
					
					System.out.println(title);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	@Override
	public void queryByMovieGenre() {
		int count = 1;
		String genres = ""; //$NON-NLS-1$
		HashMap<Integer, String> menuItems = new HashMap<>();
		for (EGenre iterable_element : EGenre.values()) {
			menuItems.put(count, iterable_element.getGenreName());
			count++;
			
		}
		System.out.println(Messages.getString("ClientBusiness.23")); //$NON-NLS-1$
		
		int input = BAUtils.menu(menuItems);
		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement statement = null;
		String query = "select m.title from movies as m JOIN genres as g   on m.movie_id = g.movie_id " //$NON-NLS-1$
				+ "where genres1=? or genres2=? or " + "genres3=? or " + "genres4=? or " + "genres5=? or " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ "genres6=? or " + "genres7=?  " + "  limit 10"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		statement = DatabaseUtils.createPreparedStatement(query);
		
		try {
			switch (input) {
				case 1:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.ACTION.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 2:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.ADVENTURE.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 3:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.ANIMATION.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 4:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.CHILDREN.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 5:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.COMEDY.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 6:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.CRIME.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 7:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.DOCUMENTARY.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 8:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.DRAMA.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 9:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.FANTASY.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 10:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.FILM_NOIR.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 11:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.HOROR.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 12:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.MUSICAL.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 13:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.MYSTERY.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 14:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.ROMANCE.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 15:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.SCI_FI.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 16:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.THILLER.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 17:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.WAR.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 18:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.WESTERN.getGenreName();
						statement.setString(i, genres);
					}
					break;
				case 19:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.nogenreslisted.getGenreName();
						statement.setString(i, genres);
					}
				case 20:
					for (int i = 1; i < 8; i++) {
						genres = EGenre.UNKOWN_TYPE.getGenreName();
						statement.setString(i, genres);
					}
					break;
				
				default:
					break;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			ResultSet resultSet = statement.executeQuery();
			System.out.println("\n\t==" + genres + "=="); //$NON-NLS-1$ //$NON-NLS-2$
			while (resultSet.next()) {
				
				String title = resultSet.getString(1);
				
				System.out.println(title);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String queryRating() {
		String titleString = null;
		Connection connection = DatabaseConnection.getInstance().getConnection();
		System.out.println(Messages.getString("ClientBusiness.24")); //$NON-NLS-1$
		String name = scanner.nextLine();
		PreparedStatement statement = null;
		String query = "select min (r.rating) ,max(r.rating), m.movie_id ,m.title from movies as m " //$NON-NLS-1$
				+ "join ratings as r on m.movie_id =r.movie_id where m.title like(?) group by m.movie_id;"; //$NON-NLS-1$
		statement = DatabaseUtils.createPreparedStatement(query);
		try {
			statement.setString(1, name.concat("%")); //$NON-NLS-1$
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				String ratingMin = resultSet.getString(1);
				String ratingMax = resultSet.getString(2);
				titleString = resultSet.getString(4);
				
				System.out.printf(Messages.getString("ClientBusiness.28"), titleString, //$NON-NLS-1$
						ratingMin, ratingMax);
				return titleString;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return titleString;
	}
	
	@Override
	public String querybyName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(Messages.getString("ClientBusiness.25")); //$NON-NLS-1$
		String name = scanner.nextLine();
		
		return name;
		
	}
	
}