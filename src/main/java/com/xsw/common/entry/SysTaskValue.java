package com.xsw.common.entry;

import java.io.Serializable;

public class SysTaskValue implements Serializable {
	private static final long serialVersionUID = 4806103828562740271L;

	private int num;
	private String id;
	private String title;
	private String name;
	private String createTime;
	private String goTime;
	private String backTime;
	private String place;
	private String objective;
	private String unitPrice;
	private String totalPrice;
	private String progress;
	private String manager;
	private String executive;
	private String accounting;
	private String Finance;
	private String day;
	private String status;
	private String reason;
	private String executive_value;
	private String executive_time;
	private String accounting_value;
	private String accounting_time;
	private String manager_value;
	private String manager_time;
	private String Finance_value;
	private String Finance_time;
	private String printCount;

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

	public String getAccounting_value() {
		return accounting_value;
	}

	public void setAccounting_value(String accounting_value) {
		this.accounting_value = accounting_value;
	}

	public String getAccounting_time() {
		return accounting_time;
	}

	public void setAccounting_time(String accounting_time) {
		this.accounting_time = accounting_time;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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

	public String getGoTime() {
		return goTime;
	}

	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SysTaskValue [num=" + num + ", id=" + id + ", title=" + title + ", name=" + name + ", createTime="
				+ createTime + ", goTime=" + goTime + ", backTime=" + backTime + ", place=" + place + ", objective="
				+ objective + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", progress=" + progress
				+ ", manager=" + manager + ", executive=" + executive + ", accounting=" + accounting + ", Finance="
				+ Finance + ", day=" + day + ", status=" + status + ", reason=" + reason + ", executive_value="
				+ executive_value + ", executive_time=" + executive_time + ", accounting_value=" + accounting_value
				+ ", accounting_time=" + accounting_time + ", manager_value=" + manager_value + ", manager_time="
				+ manager_time + ", Finance_value=" + Finance_value + ", Finance_time=" + Finance_time + "]";
	}

	public String getPrintCount() {
		return printCount;
	}

	public void setPrintCount(String printCount) {
		this.printCount = printCount;
	}

}
