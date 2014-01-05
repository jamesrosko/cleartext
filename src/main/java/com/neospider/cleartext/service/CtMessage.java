package com.neospider.cleartext.service;

public class CtMessage {

	private String message;
	private String sender;
	private String receiver;
	private String time;

	public CtMessage(String message, String sender, String receiver, String time) {
		super();
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
