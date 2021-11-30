package com.bilgeadam.movie.utils;

public enum EGenre {
	
	ACTION(Messages.getString("EGenre.0"), 1), ADVENTURE(Messages.getString("EGenre.1"), 2), ANIMATION(Messages.getString("EGenre.2"), 3), CHILDREN(Messages.getString("EGenre.3"), 4), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	COMEDY(Messages.getString("EGenre.4"), 5), CRIME(Messages.getString("EGenre.5"), 6), DOCUMENTARY(Messages.getString("EGenre.6"), 7), DRAMA(Messages.getString("EGenre.7"), 8), FANTASY(Messages.getString("EGenre.8"), 9), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	FILM_NOIR(Messages.getString("EGenre.9"), 10), HOROR(Messages.getString("EGenre.10"), 11), MUSICAL(Messages.getString("EGenre.11"), 12), MYSTERY(Messages.getString("EGenre.12"), 13), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	ROMANCE(Messages.getString("EGenre.13"), 14), SCI_FI(Messages.getString("EGenre.14"), 15), THILLER(Messages.getString("EGenre.15"), 16), WAR(Messages.getString("EGenre.16"), 17), WESTERN(Messages.getString("EGenre.17"), 18), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	nogenreslisted(Messages.getString("EGenre.18"), 19), UNKOWN_TYPE(Messages.getString("EGenre.19"), 20); //$NON-NLS-1$ //$NON-NLS-2$
	
	private final int id;
	private final String genreName;
	
	EGenre(String string, int id) {
		this.genreName = string;
		this.id = id;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getGenreName() {
		return genreName;
	}
	
	public static EGenre name2EGenre(String name) {
		EGenre genres[] = EGenre.values();
		name = name.trim();
		
		for (EGenre genre : genres) {
			if (genre.name().equalsIgnoreCase(name))
				return genre;
			else {
				if (name.equalsIgnoreCase(Messages.getString("EGenre.20"))) //$NON-NLS-1$
					return SCI_FI;
				else if (name.equalsIgnoreCase(Messages.getString("EGenre.21"))) //$NON-NLS-1$
					return FILM_NOIR;
				
			}
			
		}
		return UNKOWN_TYPE;
	}
}