package com.bilgeadam.movie.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import com.bilgeadam.movie.business.Messages;
import com.bilgeadam.movie.business.MovieBusinnes;
import com.bilgeadam.movie.business.NameBusines;
import com.bilgeadam.movie.dao.abstracts.IServerDao;
import com.bilgeadam.movie.dto.MovieTsv;
import com.bilgeadam.movie.dto.NameTsv;

public class ServerDao implements IServerDao {
	
	@Override
	public String findMovieForName(ArrayList<String> knowfortitles) {
		String line = ""; //$NON-NLS-1$
		if (knowfortitles != null) {
			for (int i = 0; i < knowfortitles.size(); i++) {
				
				System.out
						.println(MovieBusinnes.getInstance().getMovies().get(knowfortitles.get(i)).getOriginalTitle());
				System.out.println(MovieBusinnes.getInstance().getMovies().get(knowfortitles.get(i)).getStartYear());
				System.out.println("---------------------------------------"); //$NON-NLS-1$
				line = line + MovieBusinnes.getInstance().getMovies().get(knowfortitles.get(i)).getOriginalTitle()
						+ "| " + MovieBusinnes.getInstance().getMovies().get(knowfortitles.get(i)).getStartYear() + "," //$NON-NLS-1$
				; // $NON-NLS-1$
			}
			
		} else {
			System.out.println(Messages.getString("ClientBusiness.29")); //$NON-NLS-1$
		}
		return line;
		
	}
	
	@Override
	public ArrayList<String> findKnowForTlitles(String name) {
		NameTsv nameTsv = new NameTsv();
		TreeMap<String, NameTsv> treeMap = new TreeMap<>();
		treeMap = NameBusines.getInstance().deserializeNames();
		nameTsv = treeMap.get(name);
		
		return nameTsv.getKnownForTitles();
		
	}
	
	@Override
	public String findMovie(ArrayList<String> knowForTitles) {
		TreeMap<String, MovieTsv> movMap = new TreeMap<>();
		
		String line = "";
		movMap = MovieBusinnes.getInstance().deserializeMovies();
		for (int i = 0; i < knowForTitles.size(); i++) {
			
			MovieTsv movieTsv = new MovieTsv();
			movieTsv = movMap.get(knowForTitles.get(i));
			line = line + movieTsv.getOriginalTitle() + "|" + movieTsv.getStartYear() + "\n";
			
		}
		
		return line;
		
	}
	
	@Override
	public String findName(String name) {
		ArrayList<String> arrayList = null;
		String line = null;
		try {
			arrayList = new ArrayList<>();
			NameTsv name1 = new NameTsv();
			name1 = NameBusines.getInstance().getNames().get(name);
			arrayList = name1.getKnownForTitles();
			line = findMovieForName(arrayList);
		} catch (Exception e) {
			line = Messages.getString("ClientBusiness.30"); //$NON-NLS-1$
		}
		
		return line;
		
	}
	
}
