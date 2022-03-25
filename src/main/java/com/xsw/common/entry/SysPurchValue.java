package com.xsw.common.entry;

import java.io.Serializable;

public class SysPurchValue implements Serializable {
	private static final long serialVersionUID = 2219272248751080887L;

	private int num;
	private String id;
	private String title;
	private String goods;
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
	private String executive;
	private String executive_value;
	private String executive_time;
	private String accounting;
	private String Finance;
	private String status;
	private String reason;

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

	public String getExecutive_value() {
		return executive_value;
	}

	public void setExecutive_value(String executive_value) {
		this.executive_value = executive_value;
	}

	public String getExecutive_time() {
		return executive_time;
	}

	public void setExecutive_time(String executive_time) {
		this.executive_time = executive_time;
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

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
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

	public String getExecutive() {
		return executive;
	}

	public void setExecutive(String executive) {
		this.executive = executive;
	}

	public String getAccounting() {
		return accounting;
	}

	public void setAccounting(String accounting) {
		this.accounting = accounting;
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
		return "SysPurchValue [num=" + num + ", id=" + id + ", title=" + title + ", goods=" + goods + ", price=" + price
				+ ", name=" + name + ", createTime=" + createTime + ", useTime=" + useTime + ", place=" + place
				+ ", project=" + project + ", progress=" + progress + ", manager=" + manager + ", manager_value="
				+ manager_value + ", manager_time=" + manager_time + ", executive=" + executive + ", executive_value="
				+ executive_value + ", executive_time=" + executive_time + ", accounting=" + accounting + ", Finance="
				+ Finance + ", status=" + status + ", reason=" + reason + "]";
	}

}
