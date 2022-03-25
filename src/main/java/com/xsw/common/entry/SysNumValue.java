package com.xsw.common.entry;

import java.io.Serializable;

public class SysNumValue implements Serializable {
	private static final long serialVersionUID = 3834297223L;

	private Integer taskCount;
	private Integer reimCount;
	private Integer purchCount;
	private Integer loanCount;
	private Integer userCount;
	private String allTask;
	private String allPurch;
	private String allReim;
	private String allLoan;

	private Integer e_taskCount;
	private Integer a_taskCount;
	private Integer m_taskCount;
	private Integer f_taskCount;

	private Integer a_reimCount;
	private Integer m_reimCount;
	private Integer f_reimCount;

	private Integer e_purchCount;
	private Integer m_purchCount;

	private Integer m_loanCount;
	private Integer f_loanCount;

	public Integer getE_taskCount() {
		return e_taskCount;
	}

	public void setE_taskCount(Integer e_taskCount) {
		this.e_taskCount = e_taskCount;
	}

	public Integer getA_taskCount() {
		return a_taskCount;
	}

	public void setA_taskCount(Integer a_taskCount) {
		this.a_taskCount = a_taskCount;
	}

	public Integer getM_taskCount() {
		return m_taskCount;
	}

	public void setM_taskCount(Integer m_taskCount) {
		this.m_taskCount = m_taskCount;
	}

	public Integer getF_taskCount() {
		return f_taskCount;
	}

	public void setF_taskCount(Integer f_taskCount) {
		this.f_taskCount = f_taskCount;
	}

	public Integer getA_reimCount() {
		return a_reimCount;
	}

	public void setA_reimCount(Integer a_reimCount) {
		this.a_reimCount = a_reimCount;
	}

	public Integer getM_reimCount() {
		return m_reimCount;
	}

	public void setM_reimCount(Integer m_reimCount) {
		this.m_reimCount = m_reimCount;
	}

	public Integer getF_reimCount() {
		return f_reimCount;
	}

	public void setF_reimCount(Integer f_reimCount) {
		this.f_reimCount = f_reimCount;
	}

	public Integer getE_purchCount() {
		return e_purchCount;
	}

	public void setE_purchCount(Integer e_purchCount) {
		this.e_purchCount = e_purchCount;
	}

	public Integer getM_purchCount() {
		return m_purchCount;
	}

	public void setM_purchCount(Integer m_purchCount) {
		this.m_purchCount = m_purchCount;
	}

	public Integer getM_loanCount() {
		return m_loanCount;
	}

	public void setM_loanCount(Integer m_loanCount) {
		this.m_loanCount = m_loanCount;
	}

	public Integer getF_loanCount() {
		return f_loanCount;
	}

	public void setF_loanCount(Integer f_loanCount) {
		this.f_loanCount = f_loanCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getAllLoan() {
		return allLoan;
	}

	public void setAllLoan(String allLoan) {
		this.allLoan = allLoan;
	}

	public Integer getLoanCount() {
		return loanCount;
	}

	public void setLoanCount(Integer loanCount) {
		this.loanCount = loanCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAllTask() {
		return allTask;
	}

	public void setAllTask(String allTask) {
		this.allTask = allTask;
	}

	public String getAllPurch() {
		return allPurch;
	}

	public void setAllPurch(String allPurch) {
		this.allPurch = allPurch;
	}

	public String getAllReim() {
		return allReim;
	}

	public void setAllReim(String allReim) {
		this.allReim = allReim;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getReimCount() {
		return reimCount;
	}

	public void setReimCount(Integer reimCount) {
		this.reimCount = reimCount;
	}

	public Integer getPurchCount() {
		return purchCount;
	}

	public void setPurchCount(Integer purchCount) {
		this.purchCount = purchCount;
	}

}
