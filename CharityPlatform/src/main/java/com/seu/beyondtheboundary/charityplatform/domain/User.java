package com.seu.beyondtheboundary.charityplatform.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity

public class User{


	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Integer id; // 用户的唯一标识

	@Column(nullable = false, length = 20) // 映射为字段，值不能为空
	private String name;

	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@Column(nullable = false, length = 20, unique = true)
	private String username; // 用户账号，用户登录时的唯一标识

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	private Integer donateId;

	private String realname;

	private boolean sex;

	private String user_id_card;

	private String tel;

	private String address;

	private String confirmation_link;

	private String avator;

	private Integer verified;

	private boolean admin;
	@Column(length = 100)

	private String password; // 登录时密码

	public User() { // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
	}

	public User(String name, String email,String username,String password) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}


	public Integer getDonateId() {
		return donateId;
	}

	public void setDonateId(Integer donateId) {
		this.donateId = donateId;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getUser_id_card() {
		return user_id_card;
	}

	public void setUser_id_card(String user_id_card) {
		this.user_id_card = user_id_card;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConfirmation_link() {
		return confirmation_link;
	}

	public void setConfirmation_link(String confirmation_link) {
		this.confirmation_link = confirmation_link;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public Integer getVerified() {
		return verified;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}