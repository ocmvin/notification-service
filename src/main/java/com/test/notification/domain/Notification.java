package com.test.notification.domain;

public class Notification {
	
	
	private String messageBody;
	private String subject;// Optional if text Notification
	private String sendToId; // User Id

	
	@Override
	public String toString() {
		return this.sendToId+","+this.messageBody+","+this.subject;
	}
}
