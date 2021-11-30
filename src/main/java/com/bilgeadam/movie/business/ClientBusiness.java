package com.bilgeadam.movie.business;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import com.bilgeadam.movie.controller.ClientController;
import com.bilgeadam.movie.dao.ClientDao;
import com.bilgeadam.movie.utils.BAUtils;
import com.bilgeadam.movie.utils.PortInformation;

public class ClientBusiness {
	Socket socket;
	ServerCommunication communication;
	ClientController client;
	private String id;
	static Scanner scanner = new Scanner(System.in);
	
	public ClientBusiness() {
		
		this.client = new ClientController(new ClientDao());
		this.id = UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		ClientBusiness clientBusiness = new ClientBusiness();
		clientBusiness.connect2Server();
		clientBusiness.menuCreate();
	}
	
	public void connect2Server() {
		try {
			
			this.communication = new ServerCommunication(this.getSocket());
			communication.introduceClient(this.id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void menuCreate() {
		try {
			BAUtils.header(Messages.getString("ClientBusiness.36"));
			String exist = BAUtils.isDatabaseExist();
			if (exist.equalsIgnoreCase("1")) {
				System.out.println(" Database Daha Önce Oluşturulmuştur...+\n");
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HashMap<Integer, String> menuItems = new HashMap<>();
		menuItems.put(1, Messages.getString("ClientBusiness.0")); //$NON-NLS-1$
		menuItems.put(2, Messages.getString("ClientBusiness.1")); //$NON-NLS-1$
		menuItems.put(3, Messages.getString("ClientBusiness.2")); //$NON-NLS-1$
		menuItems.put(4, Messages.getString("ClientBusiness.3")); //$NON-NLS-1$
		menuItems.put(5, Messages.getString("ClientBusiness.4")); //$NON-NLS-1$
		menuItems.put(6, Messages.getString("ClientBusiness.5")); //$NON-NLS-1$
		menuItems.put(7, Messages.getString("ClientBusiness.6")); //$NON-NLS-1$
		menuItems.put(10, Messages.getString("ClientBusiness.7")); //$NON-NLS-1$
		int input = BAUtils.menu(menuItems);
		do {
			
			switch (input) {
				case 1:
					this.client.createDatabase();
					
					break;
				case 2:
					this.client.deleteDatabase();
					
					break;
				case 3:
					this.client.queryByYear();
					
					break;
				case 4:
					this.client.queryByMovieGenre();
					break;
				case 5:
					this.client.queryRating();
					break;
				case 6:
					System.out.println(Messages.getString("ClientBusiness.8")); //$NON-NLS-1$
					String actorName = scanner.nextLine();
					
					try {
						String result = this.getCommunication().askForActorsMovies(actorName);
						showReply(result);
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					
					break;
				case 7:
					Messages.getInstance().changeBundle();
					break;
				case 10:
					System.out.println(Messages.getString("ClientBusiness.35"));
					System.exit(0);
					
					break;
				
				default:
					break;
			}
			
		} while (BAUtils.wantToEnd(Messages.getString("ClientBusiness.9"), //$NON-NLS-1$
				Messages.getString("ClientBusiness.10"))); //$NON-NLS-1$
		menuCreate();
		
	}
	
	public ServerCommunication getCommunication() {
		if (this.communication == null) {
			this.communication = new ServerCommunication(this.socket);
		}
		return communication;
	}
	
	public void setCommunication(ServerCommunication communication) {
		this.communication = communication;
	}
	
	public ClientController getClient() {
		return client;
	}
	
	public void startClient() {
		connect2Server();
		menuCreate();
	}
	
	public Socket getSocket() {
		if (this.socket == null) {
			try {
				this.socket = new Socket(PortInformation.adress, PortInformation.port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return socket;
	}
	
	public void showReply(String result) {
		String[] reply = result.split(",");
		for (String string : reply) {
			System.out.println(string);
		}
	}
	
}