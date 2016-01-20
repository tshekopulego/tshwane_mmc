package com.pulego.tshwanesafetymc.pojos;

public class LogonUser {
	private int user_id;
	private String user_name;
    private String user_full_name;
	private String user_phone;
	private String user_paysal;
	private String profile_img;
	private String user_email;
	public LogonUser() {
		super();
	}
    
	public LogonUser(int user_id, String user_name, String user_full_name,
			String user_phone, String user_paysal, String profile_img,
			String user_email) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_full_name = user_full_name;
		this.user_phone = user_phone;
		this.user_paysal = user_paysal;
		this.profile_img = profile_img;
		this.user_email = user_email;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_full_name() {
		return user_full_name;
	}
	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_paysal() {
		return user_paysal;
	}
	public void setUser_paysal(String user_paysal) {
		this.user_paysal = user_paysal;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
}
