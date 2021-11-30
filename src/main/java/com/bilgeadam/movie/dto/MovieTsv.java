package com.bilgeadam.movie.dto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;

import com.bilgeadam.movie.dto.abstrac.CommonPropety;
import com.bilgeadam.movie.utils.EGenre;

public class MovieTsv extends CommonPropety implements Externalizable {
	
	/**
	 * 
	 */
	
	String tconst; // String tconst (string) - alphanumeric unique identifier of the title
	String titleType; // – the type/format of the title (e.g. movie, short, tvseries, tvepisode,
						// video, etc)
	String primaryTitle; // (string) – the more popular title / the title used by the filmmakers on
							// promotional materials at the point of release
	String originalTitle; // (string) - original title, in the original language
	String isAdult; // (boolean) - 0: non-adult title; 1: adult title
	int startYear; // (YYYY) – represents the release year of a title. In the case of TV Series, it
					// is the series start year
	int endYear; // (YYYY) – TV Series end year. ‘\N’ for all other title types
	int runtimeMinutes;// – primary runtime of the title, in minutes
	EGenre[] genres;// (string array) //– includes up to three genres associated with the title
	
	public String getTconst() {
		return tconst;
	}
	
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	
	public String getTitleType() {
		return titleType;
	}
	
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	
	public String getPrimaryTitle() {
		return primaryTitle;
	}
	
	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}
	
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public String getIsAdult() {
		return isAdult;
	}
	
	public void setIsAdult(String isAdult) {
		this.isAdult = isAdult;
	}
	
	public int getStartYear() {
		return startYear;
	}
	
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	public int getEndYear() {
		return endYear;
	}
	
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	public int getRuntimeMinutes() {
		return runtimeMinutes;
	}
	
	public void setRuntimeMinutes(int runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}
	
	public EGenre[] getGenres() {
		return genres;
	}
	
	public void createGenres(int size) {
		this.genres = new EGenre[size];
	}
	
	@Override
	public String toString() {
		return "MovieTsv [tconst=" + tconst + ", titleType=" + titleType + ", primaryTitle=" + primaryTitle
				+ ", originalTitle=" + originalTitle + ", isAdult=" + isAdult + ", startYear=" + startYear
				+ ", endYear=" + endYear + ", runtimeMinutes=" + runtimeMinutes + ", genres=" + Arrays.toString(genres)
				+ "]";
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		try {
			out.writeUTF(tconst);
			out.writeUTF(titleType);
			out.writeUTF(originalTitle);
			out.writeUTF(isAdult);
			out.writeInt(startYear);
			out.writeInt(endYear);
			out.writeInt(runtimeMinutes);
			out.writeInt(genres.length);
			for (EGenre genre : genres) {
				out.writeUTF(genre.name());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.tconst = in.readUTF();
		this.titleType = in.readUTF();
		this.originalTitle = in.readUTF();
		this.isAdult = in.readUTF();
		this.startYear = in.readInt();
		this.endYear = in.readInt();
		this.runtimeMinutes = in.readInt();
		this.genres = new EGenre[in.readInt()];
		for (int i = 0; i < genres.length; i++) {
			genres[i] = EGenre.valueOf(in.readUTF());
		}
	}
	
}