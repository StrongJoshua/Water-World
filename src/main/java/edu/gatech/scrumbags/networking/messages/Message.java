package edu.gatech.scrumbags.networking.messages;

public class Message {
	public enum MessageType {
		registration, login, userInfo, console, sendWaterReport, requestReports, infoUpdate, logout
	}

	private MessageType type;
	private String[] payload;

	/**
	 * Message for server-client relationship
	 * @param type message type
	 * @param payload message payload
	 */
	public Message (MessageType type, String... payload) {
		this.type = type;
		this.payload = payload;
	}

	/**
	 *
	 * @return Message type
	 */
	public MessageType getType () {
		return type;
	}

	/**
	 *
	 * @return Message payload
	 */
	public String[] getPayload () {
		return payload;
	}
}
