package com.xsw.common.entry;

import java.io.Serializable;

public class SysUserValue implements Serializable {
	private static final long serialVersionUID = 3834297223527649915L;

	private String id;
	private String username;
	private String name;
	private String phone;
	private String password;
	private String createTime;
	private String updateTime;
	private String role;
	private String member;
	private String status;
	private String sex;
	private String logins;

	@Override
	public String toString() {
		return "SysUserValue [id=" + id + ", username=" + username + ", name=" + name + ", phone=" + phone
				+ ", password=" + password + ", createTime=" + createTime + ", updateTime=" + updateTime + ", role="
				+ role + ", member=" + member + ", status=" + status + ", sex=" + sex + ", Logins=" + logins + "]";
	}

	public String getLogins() {
		return logins;
	}

	public void setLogins(String logins) {
		this.logins = logins;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
