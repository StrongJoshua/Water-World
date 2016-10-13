
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
	private static boolean running;
	private volatile Message handle;
	private volatile boolean request;

	public Client () {
		json = new Gson();
		try {
			socket = new Socket(InetAddress.getByName("ec2-52-25-113-216.us-west-2.compute.amazonaws.com"),
				63400);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		connected = true;
	}

	@Override
	public void run () {
		running = true;

		new Thread() {
			@Override
			public void run () {
				while (running) {
					try {
						//System.out.print(json.fromJson((String)in.readObject(), Message.class).getPayload()[0]);
						readMessage(in.readObject());
						} catch (ClassNotFoundException | IOException e) {
					}
				}
			}
		}.start();
	}
	private void quit () {
		running = false;
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
		}
	}

	public static void sendMessage (Message m) {
		if (!connected) {
			System.out.println("Not connected to server, cannot send message");
			return;
		}
		try {
			String s = json.toJson(m);
			out.writeObject(s);
			System.out.println("Sent " + s);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not send message " + m);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Could not send message " + m);
			MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
		}
	}
	private void readMessage (Object o) {
		Message m = json.fromJson((String)o, Message.class);
		if(request) {
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
		sendMessage(new Message(Message.MessageType.registration, new String[] {user.getFirst(), user.getLast(), user.getUsername(),
			password, user.getAuthorization().toString(), user.getEmail(),user.getAddress()}));
	}
	public User loginUser (User user, String password) {
		sendMessage(new Message(Message.MessageType.login, new String[] {user.getUsername(), password}));
		request = true;
		while(handle == null)
		{
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(handle.getType() != Message.MessageType.userInfo || handle.getPayload().length == 0)
		{
			return null;
		}
		String[] info = handle.getPayload();
		return new User(info[0], info[1],info[2], Authorization.valueOf(info[4]), info[5], info[6]);

	}
	public void sendWaterReport(WaterSourceReport report) {
		sendMessage(new Message(Message.MessageType.sendWaterReport, json.toJson(report)));
	}
}
