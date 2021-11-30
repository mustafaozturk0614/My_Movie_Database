package com.bilgeadam.movie.main;

import com.bilgeadam.movie.business.ServerBusiness;

public class ServerMain {
	public static void main(String[] args) {
		ServerBusiness serverBusiness = new ServerBusiness();
		
		serverBusiness.stratServer();
		
	}
}
