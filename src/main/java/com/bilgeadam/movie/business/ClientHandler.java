package com.bilgeadam.movie.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

import com.bilgeadam.movie.controller.ServerController;
import com.bilgeadam.movie.dao.ServerDao;

public class ClientHandler implements Runnable {
	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private static boolean initializing;
	private LinkedList<String> clients;
	static Scanner scanner = new Scanner(System.in);
	
	private ServerController serverController;
	
	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		this.out = null;
		this.in = null;
		this.serverController = new ServerController(new ServerDao());
		
	}
	
	@Override
	public void run() {
		try {
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String line;
			while ((line = in.readLine()) != null) {
				this.processRequest(line);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
				
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	private void processRequest(String line) {
		line = line.trim();
		
		if (this.isInitializing()) {
			this.out.println("WAIT: Server has not loaded data yet. Please wait..");
		} else {
			System.out.println("Received from client: " + line);
			if (line.startsWith("INTR:")) {
				this.registerClient(line.substring(5));
				System.out.println("Number of clients connected: " + this.numClients());
				this.out.println("Welcome " + line.substring(5));
			} else {
				
				if (line.startsWith("FILMS:")) { // "FILMS:Ingmar Bergman"
					String actorName = line.substring(6);
					line = "";
					line = this.serverController.findName(actorName);
					// line =
					// this.serverController.findMovie(this.serverController.findKnowForTlitles(actorName));
					if (line.equalsIgnoreCase("")) {
						line = "Kayıt bulunamadı";
					}
				}
				
				// else if (line.startsWith("YEAR:")) { // "YEAR:1984
				// int year = Integer.parseInt(line.substring(5));
				// line = CommonData.getInstance().getMoviesByYear(year);
				// if (line == null) {
				// line = "Kay�t bulunamad�";
				// }
				// }
				this.out.println(line);
			}
		}
		this.out.flush();
	}
	
	public static void initializingStarted() {
		try {
			initializing = true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void initializingStopped() {
		initializing = false;
	}
	
	public boolean isInitializing() {
		return initializing;
	}
	
	public void registerClient(String clientId) {
		this.getClients().add(clientId);
	}
	
	private LinkedList<String> getClients() {
		if (this.clients == null) {
			this.clients = new LinkedList<String>();
		}
		return this.clients;
	}
	
	public int numClients() {
		return this.getClients().size();
	}
	
}
