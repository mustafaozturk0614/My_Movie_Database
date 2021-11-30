package com.bilgeadam.movie.business;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.bilgeadam.movie.dto.MovieTsv;
import com.bilgeadam.movie.utils.EGenre;
import com.bilgeadam.movie.utils.FileInformation;

public class MovieBusinnes {
	private TreeMap<String, MovieTsv> movies;
	
	private static MovieBusinnes instance;
	
	private MovieBusinnes() {
		this.movies = new TreeMap<>();
		
	}
	
	public static MovieBusinnes getInstance() {
		if (instance == null) {
			
			instance = new MovieBusinnes();
		}
		return instance;
		
	}
	
	public MovieTsv parse(String line) {
		MovieTsv movie = new MovieTsv();
		StringTokenizer tokenizer = new StringTokenizer(line, "\t");
		movie.setTconst(tokenizer.nextToken());
		movie.setTitleType(tokenizer.nextToken());
		movie.setPrimaryTitle(tokenizer.nextToken());
		movie.setOriginalTitle(tokenizer.nextToken());
		movie.setIsAdult(tokenizer.nextToken());
		
		String token = tokenizer.nextToken();
		if (token.equalsIgnoreCase("\\N") || token == null) {
			movie.setStartYear(0);
		} else {
			movie.setStartYear(Integer.parseInt(token));
		}
		
		token = tokenizer.nextToken();
		if (token.equalsIgnoreCase("\\N") || token == null) {
			movie.setEndYear(0);
		} else {
			movie.setEndYear(Integer.parseInt(token));
		}
		
		token = tokenizer.nextToken();
		if (token.equalsIgnoreCase("\\N")) {
			movie.setRuntimeMinutes(0);
		} else {
			movie.setRuntimeMinutes(Integer.parseInt(token));
		}
		
		token = tokenizer.nextToken();
		StringTokenizer genreTokens = new StringTokenizer(token, ",");
		int numGenres = genreTokens.countTokens();
		movie.createGenres(numGenres);
		for (int i = 0; i < numGenres; i++) {
			movie.getGenres()[i] = EGenre.name2EGenre(genreTokens.nextToken());
		}
		
		return movie;
	}
	
	public void serializeTsv(MovieTsv movie, String path) {
		ObjectOutputStream objectOutputStream;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(path, true));
			objectOutputStream.writeObject(movie + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readMovies() {
		System.out.println(LocalDateTime.now() + "Reading movies data");
		
		File file = new File(FileInformation.TSV_PATH_MOVİES_DATA);
		
		int movieCnt = 0;
		try (FileReader fR = new FileReader(file); BufferedReader bR = new BufferedReader(fR);) {
			
			while (true) {
				String line;
				try {
					line = bR.readLine();
					if (line == null) {
						break;
					}
					if (line.startsWith("tconst")) {
						continue;
					}
					MovieTsv movie = parse(line);
					addMovie(movie);
					movieCnt++;
					
				} catch (Error err) {
					System.out.println(movieCnt);
					err.printStackTrace();
				} catch (IOException ex) {
					System.out.println(movieCnt);
					ex.printStackTrace();
				} catch (Throwable t) {
					System.out.println(movieCnt);
					t.printStackTrace();
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(LocalDateTime.now() + "-" + movieCnt + " records read from movies data");
	}
	
	private void addMovie(MovieTsv movie) {
		this.getMovies().put(movie.getTconst(), movie);
	}
	
	public TreeMap<String, MovieTsv> deserializeMovies() {
		System.out.println(LocalDateTime.now() + " Deserializing movies data");
		int nameCount = 0;
		try (FileInputStream fis = new FileInputStream(FileInformation.TSV_PATH_MOVİES);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			while (true) {
				MovieTsv movie = new MovieTsv();
				try {
					movie.readExternal(ois);
				} catch (EOFException ex) {
					break;
				}
				if (movie.getTconst() == null || (movie.getTconst() != null && movie.getTconst().isEmpty())) {
					break;
				} else {
					nameCount++;
					this.getMovies().put(movie.getTconst(), movie);
				}
			}
		} catch (Exception ex) {
			System.out.println("Read name count: " + nameCount);
			ex.printStackTrace();
		}
		System.out.println(
				LocalDateTime.now() + " Deserialization of movies data ended. " + nameCount + " records loaded");
		return this.getMovies();
		
	}
	
	public void serializeData() {
		System.out.println(LocalDateTime.now() + " Serialize işlemi başladı ");
		try (FileOutputStream fos = new FileOutputStream(FileInformation.TSV_PATH_MOVİES);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			
			Set<Map.Entry<String, MovieTsv>> toSerialize = this.getMovies().entrySet();
			// toSerialize.forEach(System.out::println);
			for (Map.Entry<String, MovieTsv> entry : toSerialize) {
				entry.getValue().writeExternal(oos);
				
				oos.flush();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(LocalDateTime.now() + " Serialize işlemi sona erdi");
	}
	
	public boolean movieDataExisting() {
		File file = new File(FileInformation.TSV_PATH_MOVİES);
		return file.exists();
	}
	
	public TreeMap<String, MovieTsv> importData() {
		System.out.println(LocalDateTime.now() + " Enter importData()");
		
		if (this.movieDataExisting())
			this.deserializeMovies();
		else {
			this.readMovies();
			this.serializeData();
		}
		
		System.out.println(LocalDateTime.now() + " exit importData()");
		return this.instance.getMovies();
	}
	
	public TreeMap<String, MovieTsv> getMovies() {
		if (this.movies == null) {
			this.movies = new TreeMap<String, MovieTsv>();
		}
		return this.movies;
	}
	
	public void setMovies(TreeMap<String, MovieTsv> movies) {
		this.movies = movies;
	}
	
}
