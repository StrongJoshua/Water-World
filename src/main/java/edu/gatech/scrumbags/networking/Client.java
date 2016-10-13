package edu.gatech.scrumbags.networking;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import edu.gatech.scrumbags.model.WaterSourceReport;
import edu.gatech.scrumbags.networking.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.google.gson.Gson;

public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private static ObjectOutputStream out;
	private static Gson json;
	private static boolean connected = false;
	private volatile boolean running;
	private volatile Message handle = null;
	private volatile boolean request;

	public Client () {
		json = new Gson();
	}

	@Override public void run () {
		running = true;
		try {
			socket = new Socket(InetAddress.getByName("ec2-52-25-113-216.us-west-2.compute.amazonaws.com"), 63400);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			if(running)
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
						if(running)
						disconnect();
					}
				}
			}
		}.start();
	}

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
		}
	}
	public void disconnect()
	{
		quit();
		MainFXApplication.disconnect();
	}


	public void sendMessage (Message m) {
		if (!connected) {
			System.out.println("Not connected to server, cannot send message");
			return;
		}
		try {
			String s = json.toJson(m);
			out.writeObject(s);
			System.out.println("Sent " + s);
		}  catch (Exception e) {
			System.out.println("Could not send message " + m);
			if(running)
			disconnect();
		}
	}

	private void readMessage (Object o) {
		Message m = json.fromJson((String)o, Message.class);
		System.out.println(o);
		if (request) {
			request = false;
			handle = m;
			return;
		}
		System.out.println("Recieved: " + o);
		if (m.getType() == Message.MessageType.registration) {

		} else if (m.getType() == Message.MessageType.login) {

		} else if (m.getType() == Message.MessageType.console) {

		} else if (m.getType() == Message.MessageType.requestReports) {

		}

	}

	public void registerUser (User user, String password) {
		sendMessage(new Message(Message.MessageType.registration,
			new String[] {user.getFirst(), user.getLast(), user.getUsername(), password, user.getAuthorization().toString(),
				user.getEmail(), user.getAddress()}));
	}

	public User loginUser (String username, String password) {
		sendMessage(new Message(Message.MessageType.login, new String[] {username, password}));
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

	public void sendWaterReport (WaterSourceReport report) {
		sendMessage(new Message(Message.MessageType.sendWaterReport, json.toJson(report)));
	}

	public void updateUserInfo (String email, String address) {
		sendMessage(new Message(Message.MessageType.infoUpdate, new String[] {email, address}));
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
	public void logout()
	{
		sendMessage(new Message(Message.MessageType.logout));
	}
}
