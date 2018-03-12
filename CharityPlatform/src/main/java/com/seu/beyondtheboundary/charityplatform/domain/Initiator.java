package com.seu.beyondtheboundary.charityplatform.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 * User 实体
 * 
 * @since 1.0.0 2017年3月5日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
public class Initiator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "发起机构姓名不能为空")
	@Size(min=2, max=20)
	@Column(nullable = false, length = 20) // 映射为字段，值不能为空
	private String name;

	@NotEmpty(message = "发起机构邮箱不能为空")
	@Size(max=50)
	@Email(message= "邮箱格式不对" )
	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@NotEmpty(message = "账号不能为空")
	@Size(min=3, max=20)
	@Column(nullable = false, length = 20, unique = true)
	private String username; // 用户账号，用户登录时的唯一标识

	@NotEmpty(message = "密码不能为空")
	@Size(max=100)
	@Column(length = 100)
	private String password; // 登录时密码

	@Column(length = 200)
	private String avatar; // 头像图片地址


	protected Initiator() { // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
	}

	public Initiator(String name, String email, String username, String password) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, username='%s', name='%s', email='%s', password='%s']", id, username, name, email,
				password);
	}
}
