package com.bilgeadam.movie.dto;

import java.net.Socket;

import com.bilgeadam.movie.utils.InformatıonSocket;

public class Client {
	
	InformatıonSocket socket;
	
	public Client() {
		
	}
	
	public Socket getsSocket() {
		return InformatıonSocket.getInstance().getSocket();
	}
	
	public void setSocket(InformatıonSocket socket) {
		this.socket = socket;
	}
	
}
