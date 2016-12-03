
package scrumbags.aquafindamobile;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import scrumbags.aquafindamobile.Message.MessageType;

/**
 * The client class of the main application
 */

public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private static ObjectOutputStream out;
	private static Gson json;
	private static boolean connected = false;
	private volatile boolean running;
	private volatile Message handle = null;
	private volatile boolean request;
	private boolean loggedIn = false;

	/** Constructs client object */
	public Client () {
		json = new Gson();
	}

	@Override
	public void run () {
		running = true;
		try {
			socket = new Socket(InetAddress.getByName("ec2-52-25-113-216.us-west-2.compute.amazonaws.com"), 63400);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			if (running) disconnect();
		}

		new Thread() {
			@Override
			public void run () {
				while (running) {
					try {
						// System.out.print(json.fromJson((String)in.readObject(), Message.class).getPayload()[0]);
						// System.out.println("Waiting");
						if (in != null) readMessage(in.readObject());
					} catch (ClassNotFoundException | IOException e) {
						if (running) disconnect();
					}
				}
			}
		}.start();

		connected = true;
	}

	/** This method will stop the client entirely */
	public void quit () {
		running = false;
		try {
			if (in != null) in.close();
			if (out != null) out.close();
			if (socket != null) socket.close();
		} catch (IOException e) {
			System.out.println("Error closing connection with server.");
		}
		connected = false;
	}

	/** This method will reattempt connection if it is lost */
	private void disconnect () {
		quit();
		//MainFXApplication.disconnect();
	}

	/** Sends message and payload to server.
	 *
	 * @param m Message with type and payload */
	private void sendMessage (Message m) {
		if (!connected) {
			// System.out.println("Not connected to server, cannot send message");
			return;
		}
		try {
			String s = json.toJson(m);
			out.writeObject(s);
			// System.out.println("Sent " + s);
		} catch (Exception e) {
			// System.out.println("Could not send message " + m);
			if (running) disconnect();
		}
	}

	/** Reads incoming messages from server
	 *
	 * @param o Object sent from server. Gson will convert to Message. */
	private void readMessage (Object o) {
		Message m = json.fromJson((String)o, Message.class);
		// System.out.println(o);
		if (request) {
			request = false;
			handle = m;
			return;
		}
	}

	/** Pulls values from user object to send and also reads password field. Sends data to server to store.
	 *
	 * @param user User object containing all user info
	 * @param password User's password, never stored locally
	 * @return the user registered
	 */
	public User registerUser (User user, String password) {
		sendMessage(new Message(Message.MessageType.registration, user.getFirst(), user.getLast(), user.getUsername(), password,
				user.getAuthorization().toString(), user.getEmail(), user.getAddress()));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((handle.getType() != MessageType.userInfo) || (handle.getPayload().length == 0)) {
			handle = null;
			return null;
		}
		String[] info = handle.getPayload();
		handle = null;
		return new User(info[0], info[1], info[2], Authorization.valueOf(info[3]), info[4], info[5]);
	}

	/** Deletes an account from the database */
	public void deleteAccount () {
		sendMessage(new Message(Message.MessageType.deleteAccount));
		//MainFXApplication.logout();
	}

	/** Sends username and password for verification server-side. If successful, returns user's information
	 *
	 * @param username User's username
	 * @param password User's password
	 * @return User object with all associated info */
	public User loginUser (String username, String password) {
		sendMessage(new Message(Message.MessageType.login, username, password));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((handle.getType() != MessageType.userInfo) || (handle.getPayload().length == 0)) {
			handle = null;
			return null;
		}
		String[] info = handle.getPayload();
		handle = null;
		loggedIn = true;
		return new User(info[0], info[1], info[2], Authorization.valueOf(info[3]), info[4], info[5]);

	}

	/** This method sends data from a source report to the database server for storage
	 * @param report Water source report to be stored
	 * @return True if report is successfully stored, false otherwise */
	/*
	public boolean sendSourceReport (WaterSourceReport report) {
		sendMessage(new Message(Message.MessageType.sourceReport, json.toJson(report)));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((handle.getType() != MessageType.sourceReport) || (handle.getPayload().length == 0)) {
			handle = null;
			return false;
		}
		handle = null;
		return true;
	}
	*/

	/** This method sends data from a purity report to the database server for storage
	 * @param report Water purity report to be stored
	 * @param source Water source report the purity report belongs to
	 * @return True if report is successfully stored, false otherwise */
	/*
	public boolean sendPurityReport (WaterPurityReport report, WaterSourceReport source) {
		sendMessage(new Message(Message.MessageType.purityReport, source.getId() + "", json.toJson(report)));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((handle.getType() != MessageType.purityReport) || (handle.getPayload().length == 0)) {
			handle = null;
			return false;
		}
		handle = null;
		return true;
	}
	*/

	/** This method will load all water reports from the database server into the client application */
	/*
	public void requestAllReports () {
		sendMessage(new Message(Message.MessageType.requestAllReports));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((handle.getType() != MessageType.requestAllReports) || (handle.getPayload().length == 0)) {
			return;
		}
		int count = 0;
		for (String s : handle.getPayload()) {
			WaterSourceReport ws = json.fromJson(s, WaterSourceReport.class);
			MainFXApplication.waterReports.add(ws);
			MainFXApplication.purityMap.put(ws, new LinkedList<>());
			if (ws.getPurityReports() != null) {
				for (WaterPurityReport p : ws.getPurityReports()) {
					MainFXApplication.purityMap.get(ws).add(p);
					MainFXApplication.waterReports.add(p);
					count++;
				}
			}
			count++;
		}
		WaterReport.reportCount = count;
		handle = null;
	}
	*/

	/** Sends user info to the database server to updates user's info
	 * @param email User's inputted email
	 * @param address User's inputted physical address */
	public void updateUserInfo (String email, String address) {
		sendMessage(new Message(Message.MessageType.infoUpdate, email, address));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((handle.getType() != MessageType.infoUpdate) || (handle.getPayload().length == 0)) {
			handle = null;
			return;
		}
		//MainFXApplication.userInfo.setEmail(handle.getPayload()[0]);
		//MainFXApplication.userInfo.setAddress(handle.getPayload()[1]);
		handle = null;
	}

	/** Sends logout information */
	public void logout () {
		sendMessage(new Message(Message.MessageType.logout));
		loggedIn = false;
	}

	/** @return If the client is currently connected to the server. */
	public boolean isConnected () {
		return connected;
	}

	/** Used by the JUnit tests to register an account.
	 * @param username Username to register.
	 * @param password Password to register with.
	 * @return The message returned by the server. */
	public Message jUnitRegister (String username, String password) {
		sendMessage(new Message(MessageType.registration, "", "", username, password, Authorization.user.toString(), "", ""));
		request = true;
		while (running && (handle == null)) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Message m = handle;
		handle = null;
		return m;
	}

	/** Used by JUnit tests to delete an account. */
	public void jUnitDelete () {
		sendMessage(new Message(Message.MessageType.deleteAccount));
		logout();
	}

	/** @return If the client is currently logged into an account */
	public boolean isLoggedIn () {
		return loggedIn;
	}
}
