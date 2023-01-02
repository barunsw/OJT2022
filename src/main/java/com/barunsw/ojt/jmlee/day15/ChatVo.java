package com.barunsw.ojt.jmlee.day15;

import java.util.Calendar;

public class ChatVo {

	private String sender;
	private String message;
	private String send_Time;
	
	private Calendar cal = Calendar.getInstance();

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ChatVo(String sender, String message) {
		this.sender = sender;
		this.message = message;
		
		String hour = String.valueOf(cal.get(Calendar.HOUR));
		String min= String.valueOf(cal.get(Calendar.MINUTE));
		String sec = String.valueOf(cal.get(Calendar.SECOND));
		send_Time = "[" + hour + ":" + min + ":" + sec + "]";
	}
	
	@Override
	public String toString() {
		return send_Time + "["+this.sender+"] : "+ message + "\n";
	}

	
}
