package com.bilgeadam.movie.dto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class NameTsv implements Externalizable {
	/**
	 * 
	 */
	
	private String nconst;// (string) alphanumeric unique identifier of the name/person
	private String primaryName; // (string)– name by which the person is most often credited
	private int birthYear; // – in YYYY format
	private int deathYear;// – in YYYY format if applicable, else '\N'
	private ArrayList<String> primaryProfession; // (array of strings)– the top-3 professions of the person
	private ArrayList<String> knownForTitles; // (array of tconsts) – titles the person is known for
	
	public String getNconst() {
		return nconst;
	}
	
	public void setNconst(String nconst) {
		this.nconst = nconst;
	}
	
	public String getPrimaryName() {
		return primaryName;
	}
	
	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	public int getDeathYear() {
		return deathYear;
	}
	
	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}
	
	public ArrayList<String> getPrimaryProfession() {
		if (this.primaryProfession == null) {
			this.primaryProfession = new ArrayList<>();
		}
		return this.primaryProfession;
	}
	
	public ArrayList<String> getKnownForTitles() {
		if (this.knownForTitles == null) {
			this.knownForTitles = new ArrayList<>();
		}
		return this.knownForTitles;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		try {
			out.writeUTF(nconst);
			out.writeUTF(primaryName);
			out.writeInt(birthYear);
			out.writeInt(deathYear);
			if (primaryProfession == null) {
				out.writeInt(0);
			} else {
				out.writeInt(primaryProfession.size());
				for (String profession : primaryProfession) {
					out.writeUTF(profession);
				}
			}
			if (knownForTitles == null) {
				out.writeInt(0);
			} else {
				
				out.writeInt(knownForTitles.size());
				for (String title : knownForTitles) {
					out.writeUTF(title);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
		this.nconst = in.readUTF();
		this.primaryName = in.readUTF();
		this.birthYear = in.readInt();
		this.deathYear = in.readInt();
		int len = in.readInt();
		if (len != 0) {
			this.primaryProfession = new ArrayList<>();
			for (int i = 0; i < len; i++) {
				this.primaryProfession.add(i, in.readUTF());
			}
		}
		
		len = in.readInt();
		if (len != 0) {
			this.knownForTitles = new ArrayList<>();
			for (int i = 0; i < len; i++) {
				this.knownForTitles.add(i, in.readUTF());
			}
		}
		
	}
	
	@Override
	public String toString() {
		return "NameTsv [nconst=" + nconst + ", primaryName=" + primaryName + ", birthYear=" + birthYear
				+ ", deathYear=" + deathYear + ", primaryProfession=" + primaryProfession + ", knownForTitles="
				+ knownForTitles + "]";
	}
	
	// private static NameTsv parse(String line) {
	// NameTsv name = new NameTsv();
	// StringTokenizer tokenizer = new StringTokenizer(line, "\t");
	// name.nconst = tokenizer.nextToken();
	// name.primaryName = tokenizer.nextToken();
	//
	// String token = tokenizer.nextToken();
	// if (token.equalsIgnoreCase("\\N") || token == null) {
	// name.birthYear = 0;
	// } else {
	// name.birthYear = Integer.parseInt(token);
	//
	// token = tokenizer.nextToken();
	// if (token.equalsIgnoreCase("\\N") || token == null) {
	// name.deathYear = 0;
	// } else {
	// name.deathYear = Integer.parseInt(token);
	// }
	//
	// token = tokenizer.nextToken();
	// if (token.equalsIgnoreCase("\\N")) {
	// name.primaryProfession = new String[1];
	// name.primaryProfession[0] = "tanımsız";
	// } else {
	// StringTokenizer professions = new StringTokenizer(token, ",");
	// int numProfessions = professions.countTokens();
	// name.primaryProfession = new String[numProfessions];
	// for (int i = 0; i < numProfessions; i++) {
	// name.primaryProfession[i] = professions.nextToken().strip();
	// }
	// }
	//
	// if (tokenizer.hasMoreTokens()) {
	// token = tokenizer.nextToken();
	// if (token.equalsIgnoreCase("\\N")) {
	// name.knownForTitles = new String[1];
	// name.knownForTitles[0] = "tanımsız";
	// } else {
	// StringTokenizer titles = new StringTokenizer(token, ",");
	// int numTitles = titles.countTokens();
	// name.knownForTitles = new String[numTitles];
	// for (int i = 0; i < numTitles; i++) {
	// name.knownForTitles[i] = titles.nextToken().strip();
	// }
	// }
	// }
	// }
	// return name;
	// }
	//
	// public static void read() {
	// System.out.println("Reading names data");
	//
	// File file = new File(FileInformation.TSV_PATH_NAMES_DATA);
	//
	// int nameCnt = 0;
	// try (FileReader fR = new FileReader(file); BufferedReader bR = new
	// BufferedReader(fR);) {
	//
	// while (true) {
	// String line;
	// try {
	// line = bR.readLine();
	// if (line == null) {
	// break;
	// }
	// if (line.startsWith("nconst")) {
	// continue;
	// }
	// NameTsv name = NameTsv.parse(line);
	// nameCnt++;
	// NameBusines.getInstance().addName(name);
	// ;
	// } catch (Error err) {
	// System.out.println(nameCnt);
	// err.printStackTrace();
	// } catch (IOException ex) {
	// System.out.println(nameCnt);
	// ex.printStackTrace();
	// } catch (Throwable t) {
	// System.out.println(nameCnt);
	// t.printStackTrace();
	// }
	// }
	// } catch (IOException ex) {
	//
	// }
	// System.out.println(nameCnt + " records read from names data");
	// }
	//
	// public void writeExternal(ObjectOutput out) {
	// try {
	// out.writeUTF(nconst);
	// out.writeUTF(primaryName);
	// out.writeInt(birthYear);
	// out.writeInt(deathYear);
	// if (primaryProfession == null) {
	// out.writeInt(0);
	// } else {
	// out.writeInt(primaryProfession.length);
	// for (String profession : primaryProfession) {
	// out.writeUTF(profession);
	// }
	// }
	// if (knownForTitles == null) {
	// out.writeInt(0);
	// } else {
	//
	// out.writeInt(knownForTitles.length);
	// for (String title : knownForTitles) {
	// out.writeUTF(title);
	// }
	// }
	//
	// } catch (IOException e) {
	// System.out.println(deathYear);
	// e.printStackTrace();
	// }
	//
	// }
	//
	// public void readExternal(ObjectInput in) throws IOException,
	// ClassNotFoundException {
	// this.setNconst(in.readUTF());
	// this.setPrimaryName(in.readUTF());
	// this.setBirthYear(in.readInt());
	// this.setDeathYear(in.readInt());
	//
	// int len = in.readInt();
	// if (len != 0) {
	// this.createPrimaryProfession(len);
	// for (int i = 0; i < this.getPrimaryProfession().length; i++) {
	// this.getPrimaryProfession()[i] = in.readUTF();
	// }
	// }
	//
	// len = in.readInt();
	// if (len != 0) {
	// this.createKnownForTitles(len);
	// for (int i = 0; i < this.getKnownForTitles().length; i++) {
	// this.getKnownForTitles()[i] = in.readUTF();
	// }
	// }
	// }
	
}
