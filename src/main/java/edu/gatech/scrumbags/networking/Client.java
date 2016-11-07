package edu.gatech.scrumbags.networking;

import com.google.gson.Gson;
import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.*;
import edu.gatech.scrumbags.networking.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private static ObjectOutputStream out;
	private static Gson json;
	private static boolean connected = false;
	private volatile boolean running;
	private volatile Message handle = null;
	private volatile boolean request;
	/**
	 * Constructs client object
	 */
	public Client () {
		json = new Gson();
	}

	@Override public void run () {
		running = true;
		try {
			socket = new Socket(InetAddress.getByName("ec2-52-25-113-216.us-west-2.compute.amazonaws.com"),
				63400);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			if (running)
				disconnect();
		}
		connected = true;

		new Thread() {
			@Override public void run () {
				while (running) {
					try {
						//System.out.print(json.fromJson((String)in.readObject(), Message.class).getPayload()[0]);
						//System.out.println("Waiting");
						if (in != null)
							readMessage(in.readObject());
					} catch (ClassNotFoundException | IOException e) {
						if (running)
							disconnect();
					}
				}
			}
		}.start();
	}

	/**
	 * This method will stop the client entirely
	 */
	public void quit () {
		running = false;
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			System.out.println("Error closing connection with server.");
		}
	}

	/**
	 * This method will reattempt connection if it is lost
	 */
	private void disconnect () {
		quit();
		MainFXApplication.disconnect();
	}

	/**
	 * Sends message and payload to server.
	 *
	 * @param m Message with type and paylod
	 */
	private void sendMessage (Message m) {
		if (!connected) {
			//System.out.println("Not connected to server, cannot send message");
			return;
		}
		try {
			String s = json.toJson(m);
			out.writeObject(s);
			//System.out.println("Sent " + s);
		} catch (Exception e) {
			//System.out.println("Could not send message " + m);
			if (running)
				disconnect();
		}
	}

	/**
	 * Reads incoming messages from server
	 *
	 * @param o Object sent from server. Gson will convert to Message.
	 */
	private void readMessage (Object o) {
		Message m = json.fromJson((String)o, Message.class);
		//System.out.println(o);
		if (request) {
			request = false;
			handle = m;
			return;
		}
		//System.out.println("Recieved: " + o);
		if (m.getType() == Message.MessageType.registration) {

		} else if (m.getType() == Message.MessageType.login) {

		} else if (m.getType() == Message.MessageType.console) {

		}

	}

	/**
	 * Pulls values from user object to send and also reads password field. Sends data to server to store.
	 *
	 * @param user     User object containing all user info
	 * @param password User's password, never stored locally
	 */
	public void registerUser (User user, String password) {
		sendMessage(
			new Message(Message.MessageType.registration, user.getFirst(), user.getLast(), user.getUsername(),
				password, user.getAuthorization().toString(), user.getEmail(), user.getAddress()));
	}

	/**
	 * Sends username and password for verification serverside. If successful, returns
	 * user's information
	 *
	 * @param username User's username
	 * @param password User's password
	 * @return User object with all associated info
	 */
	public User loginUser (String username, String password) {
		sendMessage(new Message(Message.MessageType.login, username, password));
		request = true;
		while (running && handle == null) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (handle.getType() != Message.MessageType.userInfo || handle.getPayload().length == 0) {
			return null;
		}
		String[] info = handle.getPayload();
		handle = null;
		return new User(info[0], info[1], info[2], Authorization.valueOf(info[3]), info[4], info[5]);

	}

	/**
	 * Method is not done.
	 *
	 * @param report Water report to be stored
	 */
	public boolean sendSourceReport (WaterSourceReport report) {
		sendMessage(new Message(Message.MessageType.sourceReport, json.toJson(report)));
		request = true;
		while (running && handle == null) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (handle.getType() != Message.MessageType.sourceReport || handle.getPayload().length == 0) {
			return false;
		}
		System.out.println(handle.getPayload()[0]);
		handle = null;
		return true;
	}
	/**
	 * Method is not done.
	 *
	 * @param report Water report to be stored
	 */
	public boolean sendPurityReport (WaterPurityReport report, WaterSourceReport source) {
		sendMessage(new Message(Message.MessageType.purityReport, source.getId() + "", json.toJson(report)));
		request = true;
		while (running && handle == null) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (handle.getType() != Message.MessageType.purityReport || handle.getPayload().length == 0) {
			return false;
		}
		handle = null;
		return true;
	}

	/**
	 * Method is not done
	 */
	public void requestAllReports () {
		sendMessage(new Message(Message.MessageType.requestAllReports));
		request = true;
		while (running && handle == null) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (handle.getType() != Message.MessageType.requestAllReports|| handle.getPayload().length == 0) {
			return;
		}
		int count = 0;
		for(String s : handle.getPayload())
		{
			WaterSourceReport ws = json.fromJson(s, WaterSourceReport.class);
			MainFXApplication.waterReports.add(ws);
			MainFXApplication.purityMap.put(ws, new LinkedList<>());
			if(ws.getPurityReports() != null) {
				for (WaterPurityReport p : ws.getPurityReports()) {
					MainFXApplication.purityMap.get(ws).add(p);
					MainFXApplication.waterReports.add(p);
					count++;
				}
			}
			count ++;
		}
		WaterReport.reportCount = count;
		handle = null;
	}

	/**
	 * Sends user info to server to update info in database
	 *
	 * @param email   User's inputted email
	 * @param address User's inputted physical address
	 */
	public void updateUserInfo (String email, String address) {
		sendMessage(new Message(Message.MessageType.infoUpdate, email, address));
		request = true;
		while (running && handle == null) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (handle.getType() != Message.MessageType.infoUpdate || handle.getPayload().length == 0) {
			//System.out.println(handle.getType() + "==" + Message.MessageType.infoUpdate + "?" + (handle.getType() != Message.MessageType.infoUpdate));
			//System.out.println(handle.getPayload().length);
			//System.out.println("Returning");
			return;
		}
		MainFXApplication.userInfo.setEmail(handle.getPayload()[0]);
		MainFXApplication.userInfo.setAddress(handle.getPayload()[1]);
		handle = null;
	}

	/**
	 * Sends logout information
	 */
	public void logout () {
		sendMessage(new Message(Message.MessageType.logout));
	}

	public boolean isConnected()
	{
		return connected;
	}

}
