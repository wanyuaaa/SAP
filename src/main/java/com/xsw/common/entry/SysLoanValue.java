package com.xsw.common.entry;

import java.io.Serializable;

public class SysLoanValue implements Serializable {
	private static final long serialVersionUID = -2620511696459357442L;

	private int num;
	private String id;
	private String title;
	private String price;
	private String name;
	private String createTime;
	private String useTime;
	private String place;
	private String project;
	private String progress;
	private String manager;
	private String manager_value;
	private String manager_time;
	private String Finance_value;
	private String Finance_time;
	private String Finance;
	private String status;
	private String reason;

	public String getFinance_value() {
		return Finance_value;
	}

	public void setFinance_value(String finance_value) {
		Finance_value = finance_value;
	}

	public String getFinance_time() {
		return Finance_time;
	}

	public void setFinance_time(String finance_time) {
		Finance_time = finance_time;
	}

	public String getManager_value() {
		return manager_value;
	}

	public void setManager_value(String manager_value) {
		this.manager_value = manager_value;
	}

	public String getManager_time() {
		return manager_time;
	}

	public void setManager_time(String manager_time) {
		this.manager_time = manager_time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getFinance() {
		return Finance;
	}

	public void setFinance(String finance) {
		Finance = finance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SysLoanValue [num=" + num + ", id=" + id + ", title=" + title + ", price=" + price + ", name=" + name
				+ ", createTime=" + createTime + ", useTime=" + useTime + ", place=" + place + ", project=" + project
				+ ", progress=" + progress + ", manager=" + manager + ", manager_value=" + manager_value
				+ ", manager_time=" + manager_time + ", Finance_value=" + Finance_value + ", Finance_time="
				+ Finance_time + ", Finance=" + Finance + ", status=" + status + ", reason=" + reason + "]";
	}

}
