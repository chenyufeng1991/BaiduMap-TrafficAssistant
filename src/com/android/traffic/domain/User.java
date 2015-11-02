package com.android.traffic.domain;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6318243171257817512L;
	private int id;
	private String username;
	private String password;
	private String sex;
	private String birthday;

	private String phone;
	private String mail;

	// 用户注册日期
	private String registerDate;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String sex,
			String registerDate, String birthday, String phone, String mail) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.registerDate = registerDate;
		this.birthday = birthday;
		this.phone = phone;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", sex=" + sex + ", birthday=" + birthday
				+ ", phone=" + phone + ", mail=" + mail + ", registerDate="
				+ registerDate + "]";
	}

}
