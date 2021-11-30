package com.bilgeadam.movie.main;

import com.bilgeadam.movie.business.ClientBusiness;

public class ClientMain {
	public static void main(String[] args) {
		ClientBusiness clientBusiness = new ClientBusiness();
		clientBusiness.startClient();
	}
}
