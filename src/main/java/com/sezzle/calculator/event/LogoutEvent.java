package com.sezzle.calculator.event;

/**
 * 
 * @author Rajeev Massey
 */
public class LogoutEvent {
	
	private String username;

	public LogoutEvent(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
