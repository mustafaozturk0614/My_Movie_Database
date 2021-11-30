package com.bilgeadam.movie.business;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import com.bilgeadam.movie.dto.Server;
import com.bilgeadam.movie.utils.PortInformation;

public class ServerBusiness {
	Server server;
	static Scanner scanner = new Scanner(System.in);
	
	public void server() {
		
		try (ServerSocket serverSocket = new ServerSocket(PortInformation.port)) {
			serverSocket.setReuseAddress(true);
			
			String line = null;
			System.out.println("Server started and waiting for clients...");
			while (true) {
				Socket socket = serverSocket.accept();
				
				ClientHandler clienthandler = new ClientHandler(socket);
				new Thread(clienthandler).start();
				
			}
			
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}
	
	public void stratServer() {
		
		new Thread(new DataImport()).start();
		// try {
		// Thread.sleep(180000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		this.server();
		
	}
	
	public static void main(String[] args) {
		ServerBusiness serverBusiness2 = new ServerBusiness();
		serverBusiness2.stratServer();
	}
}