package com.bilgeadam.movie.business;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static Locale locale;
	private static String language = "tr";
	private static Messages instance;
	private static Locale enlocale;
	
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	
	private static ResourceBundle bundle;
	
	private Messages() {
	}
	
	public static Messages getInstance() {
		if (instance == null) {
			instance = new Messages();
		}
		return instance;
		
	}
	
	public ResourceBundle getBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BUNDLE_NAME, getLocale(language));
		}
		return bundle;
	}
	
	private static Locale getLocale(String language) {
		if (language.equalsIgnoreCase("tr")) {
			if (locale == null) {
				locale = new Locale("tr");
			}
			return locale;
		} else {
			if (enlocale == null) {
				enlocale = new Locale("en");
			}
			return enlocale;
		}
	}
	
	public void changeBundle() {
		if (language.equalsIgnoreCase("tr")) {
			language = "en";
		} else {
			language = "tr";
		}
		
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, getLocale(language));
	}
	
	public static String getString(String key) {
		try {
			bundle = getInstance().getBundle();
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
