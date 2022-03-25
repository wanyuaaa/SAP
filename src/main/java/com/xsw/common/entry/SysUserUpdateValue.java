package com.xsw.common.entry;

import java.io.Serializable;

public class SysUserUpdateValue implements Serializable {
	private static final long serialVersionUID = 3834297223527649921L;

	private String id;
	private String username;
	private String name;
	private String phone;
	private String sex;
	private String oldPassword;
	private String newPassword;

	@Override
	public String toString() {
		return "SysUserUpdateValue [id=" + id + ", username=" + username + ", name=" + name + ", phone=" + phone
				+ ", sex=" + sex + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
