package com.helper;

public class APIMessage {

	private String message;
	private String status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public APIMessage(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	public APIMessage() {
		super();
	}
	
}
