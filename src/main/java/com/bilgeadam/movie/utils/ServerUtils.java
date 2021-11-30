package com.bilgeadam.movie.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerUtils {
	public static String getRecivedMessage(Socket socket) {
		
		InputStream inputStream = null;
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream));
		String recieveMessage = null;
		try {
			recieveMessage = reader2.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recieveMessage;
		
	}
	
	public static void sendToClient(Socket socket, String string) {
		
		System.out.println("::");
		
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		
		printWriter.println(string);
		
	}
	
	public static void process(Socket socket) {
		
		String recievMessage;
		
		while (true) {
			
			if ((recievMessage = getRecivedMessage(socket)) != null) {
				System.out.println("Client:" + recievMessage);
				
			}
			//
			// sendToClient(socket,String name);
		}
	}
	
}
