package com.pulego.tshwanesafetymc.pojos;

public class ChangePassword {
   private String payNumber;
   private String email;
   private String password;
	public ChangePassword(String payNumber, String email, String password) {
		super();
		this.payNumber = payNumber;
		this.email = email;
		this.password = password;
	}
	public String getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
   
}
