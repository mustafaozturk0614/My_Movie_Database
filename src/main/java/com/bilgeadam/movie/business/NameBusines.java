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
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.bilgeadam.movie.dto.NameTsv;
import com.bilgeadam.movie.utils.FileInformation;

public class NameBusines {
	private static NameBusines instance;
	private TreeMap<String, NameTsv> names;
	
	private NameBusines() {
		
	}
	
	public static NameBusines getInstance() {
		if (instance == null) {
			instance = new NameBusines();
			
		}
		return instance;
	}
	
	public NameTsv parse(String line) {
		NameTsv name = new NameTsv();
		StringTokenizer tokenizer = new StringTokenizer(line, "\t");
		
		name.setNconst(tokenizer.nextToken());
		
		name.setPrimaryName(tokenizer.nextToken());
		String token = tokenizer.nextToken();
		if (token.equalsIgnoreCase("\\N") || token == null) {
			name.setBirthYear(0);
		} else {
			name.setBirthYear(Integer.parseInt(token));
		}
		token = tokenizer.nextToken();
		if (token.equalsIgnoreCase("\\N") || token == null) {
			name.setDeathYear(0);
		} else {
			name.setDeathYear(Integer.parseInt(token));
		}
		if (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();
			StringTokenizer tokenizerprofession = new StringTokenizer(token, ",");
			int size = tokenizerprofession.countTokens();
			for (int i = 0; i < size; i++) {
				name.getPrimaryProfession().add(i, tokenizerprofession.nextToken());
			}
		}
		if (tokenizer.hasMoreTokens()) {
			
			token = tokenizer.nextToken();
			StringTokenizer tokenizerGenre = new StringTokenizer(token, ",");
			int size = tokenizerGenre.countTokens();
			for (int i = 0; i < size; i++) {
				name.getKnownForTitles().add(i, tokenizerGenre.nextToken());
			}
		}
		
		return name;
		// serializeData(name);
		// addName(name);
		
	}
	
	public String getRecivedMessage(Socket socket) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void sendToMessage(Socket socket, String string) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean nameDataExisting() {
		File file = new File(FileInformation.TSV_PATH_NAMES);
		return file.exists();
	}
	
	public TreeMap<String, NameTsv> deserializeNames() {
		System.out.println(LocalDateTime.now() + " Deserializing names data");
		int nameCount = 0;
		try (FileInputStream fis = new FileInputStream(FileInformation.TSV_PATH_NAMES);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			while (true) {
				NameTsv name = new NameTsv();
				try {
					name.readExternal(ois);
				} catch (EOFException ex) {
					break;
				}
				if (name.getNconst() == null || (name.getNconst() != null && name.getNconst().isEmpty())) {
					break;
				} else {
					nameCount++;
					this.getNames().put(name.getPrimaryName(), name);
					
				}
			}
		} catch (Exception ex) {
			System.out.println("Read name count: " + nameCount);
			ex.printStackTrace();
		}
		System.out.println(
				LocalDateTime.now() + " Deserialization of names data ended. " + nameCount + " records loaded");
		return names;
		
	}
	
	public void readNames() {
		System.out.println(LocalDateTime.now() + "Names Read işlemi ");
		File file = new File(FileInformation.TSV_PATH_NAMES_DATA);
		
		int nameCnt = 0;
		try (FileReader fR = new FileReader(file); BufferedReader bR = new BufferedReader(fR);) {
			
			while (true) {
				String line;
				try {
					line = bR.readLine();
					if (line == null) {
						break;
					}
					if (line.startsWith("nconst")) {
						continue;
					}
					
					nameCnt++;
					
					NameTsv name = parse(line);
					addName(name);
				} catch (Error err) {
					System.out.println(nameCnt);
					err.printStackTrace();
				} catch (IOException ex) {
					System.out.println(nameCnt);
					ex.printStackTrace();
				} catch (Throwable t) {
					System.out.println(nameCnt);
					t.printStackTrace();
				}
			}
		} catch (IOException ex) {
			
		}
		System.out.println(nameCnt);
		
	}
	
	public void serializeData() {
		System.out.println(LocalDateTime.now() + " Serialize işlemi başladı ");
		try (FileOutputStream fos = new FileOutputStream(FileInformation.TSV_PATH_NAMES);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			
			Set<Map.Entry<String, NameTsv>> toSerialize = this.getNames().entrySet();
			// toSerialize.forEach(System.out::println);
			for (Map.Entry<String, NameTsv> entry : toSerialize) {
				entry.getValue().writeExternal(oos);
				
				oos.flush();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(LocalDateTime.now() + " Serialize işlemi sona erdi");
	}
	
	public void addName(NameTsv name) {
		this.getNames().put(name.getPrimaryName(), name);
		
	}
	
	public TreeMap<String, NameTsv> getNames() {
		if (this.names == null) {
			this.names = new TreeMap<>();
			
		}
		
		return this.names;
	}
	
	public void setNames(TreeMap<String, NameTsv> names) {
		this.names = names;
	}
	
	public TreeMap<String, NameTsv> importData() {
		System.out.println(LocalDateTime.now() + " Enter importData()");
		
		if (this.nameDataExisting())
			this.deserializeNames();
		else {
			this.readNames();
			this.serializeData();
		}
		
		System.out.println(LocalDateTime.now() + " exit importData()");
		return this.instance.getNames();
	}
	
	@Override
	public String toString() {
		return "NameBusines [names=" + names + "]";
	}
	
}
