package edu.gatech.scrumbags.networking.messages;

public class Message {
	enum MessageType {
		registration, userInfo
	}

	private String[] payload;

	public Message (String... payload) {
		this.payload = payload;
	}

	public String[] getPayload () {
		return payload;
	}
}