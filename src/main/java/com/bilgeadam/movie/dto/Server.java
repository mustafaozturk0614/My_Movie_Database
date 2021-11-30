package com.bilgeadam.movie.dto;

import java.net.ServerSocket;

import com.bilgeadam.movie.utils.InformatıonServerSocket;

public class Server {
	InformatıonServerSocket sSocket;
	
	public Server() {
		
	}
	
	public ServerSocket getsSocket() {
		return InformatıonServerSocket.getInstance().getServerSocket();
	}
	
	public void setsSocket(InformatıonServerSocket sSocket) {
		this.sSocket = sSocket;
	}
	
}
