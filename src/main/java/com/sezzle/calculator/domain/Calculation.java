package com.sezzle.calculator.domain;

/**
 * 
 * @author Rajeev Massey
 */
public class Calculation {

	private String username;
	private String expression;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public String toString() {
		return "ChatMessage [user=" + username + ", message=" + expression + "]";
	}
}
