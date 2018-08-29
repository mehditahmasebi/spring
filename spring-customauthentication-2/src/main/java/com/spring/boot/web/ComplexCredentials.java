package com.spring.boot.web;

public class ComplexCredentials {
	String password;
	String passwordSecond;
	
	public ComplexCredentials(String password, String secondSecret)
	{
		this.password = password;
		this.passwordSecond = secondSecret;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSecond() {
		return passwordSecond;
	}

	public void setPasswordSecond(String passwordSecond) {
		this.passwordSecond = passwordSecond;
	}
	
}
