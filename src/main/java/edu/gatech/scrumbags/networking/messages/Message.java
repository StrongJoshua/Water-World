
package edu.gatech.scrumbags.networking.messages;

public class Message {
	enum MessageType {
		registration, login, userInfo
	}

	private MessageType type;
	private String[] payload;

	public Message (MessageType type, String... payload) {
		this.type = type;
		this.payload = payload;
	}

	public MessageType getType () {
		return type;
	}

	public String[] getPayload () {
		return payload;
	}
}
