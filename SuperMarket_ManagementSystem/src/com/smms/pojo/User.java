package com.smms.pojo;

public class User {

	private Integer id;
	private String username;
	private String password;
	// 1 管理员  2 销售员
	private String power;
	
	public User() {
		super();
		
	}

	public User(String username, String password, String power) {
		super();
		this.username = username;
		this.password = password;
		this.power = power;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User(Integer id, String username, String password, String power) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.power = power;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", power=" + power + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
	
}
