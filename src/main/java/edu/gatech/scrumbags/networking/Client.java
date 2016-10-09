package edu.gatech.scrumbags.networking;

import java.io.IOException;
import java.net.Socket;

public class Client {
	private Socket socket;
	
	public Client () {
		try {
			socket = new Socket("172.31.37.168", 63400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
