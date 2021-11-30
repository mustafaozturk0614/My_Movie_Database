package com.bilgeadam.movie.utils;

import java.io.IOException;
import java.net.Socket;

public class InformatıonSocket {
	private static InformatıonSocket instance;
	
	Socket socket;
	PortInformation portInformation;
	String adress;
	int port;
	
	private InformatıonSocket() {
		
		this.portInformation = new PortInformation();
		this.adress = portInformation.getAdress();
		this.port = portInformation.getPort();
		try {
			this.socket = new Socket(this.adress, this.port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static InformatıonSocket getInstance() {
		
		if (instance == null) {
			
			instance = new InformatıonSocket();
		} else {
			if (instance.getSocket().isClosed()) {
				instance = new InformatıonSocket();
				
			}
			
		}
		
		return instance;
		
	}
	
	public Socket getSocket() {
		return socket;
	}
	
}
