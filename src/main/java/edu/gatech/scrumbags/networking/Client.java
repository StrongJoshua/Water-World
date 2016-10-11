
package edu.gatech.scrumbags.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private Socket socket;

	public Client () {
		try {
			socket = new Socket(InetAddress.getByName("ec2-52-25-113-216.us-west-2.compute.amazonaws.com"),
				63400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
