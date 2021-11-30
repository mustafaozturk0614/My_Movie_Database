package com.bilgeadam.movie.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InformatıonServerSocket {
	private static InformatıonServerSocket instance;
	
	ServerSocket serverSocket;
	Socket socket;
	PortInformation portInformation;
	
	int port;
	
	private InformatıonServerSocket() {
		
		this.portInformation = new PortInformation();
		
		this.port = portInformation.getPort();
		
	}
	
	public static InformatıonServerSocket getInstance() {
		
		if (instance == null) {
			
			instance = new InformatıonServerSocket();
		} else {
			
			if (instance.getServerSocket().isClosed()) {
				instance = new InformatıonServerSocket();
			}
			
		}
		
		return instance;
		
	}
	
	public ServerSocket getServerSocket() {
		if (this.serverSocket == null) {
			try {
				serverSocket = new ServerSocket(this.port);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return this.serverSocket;
	}
	
	public Socket getSocket() {
		if (this.socket == null) {
			try {
				socket = this.serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return socket;
	}
}
