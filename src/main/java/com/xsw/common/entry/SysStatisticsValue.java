package com.xsw.common.entry;

import java.io.Serializable;
import java.util.List;

public class SysStatisticsValue implements Serializable {
	private static final long serialVersionUID = -1614791923812734466L;

	private List<String> first;
	private List<String> second;
	private List<String> third;
	private List<String> fourth;
	private List<String> fifth;
	private List<String> sixth;
	private List<String> date;

	public List<String> getFirst() {
		return first;
	}

	public void setFirst(List<String> first) {
		this.first = first;
	}

	public List<String> getSecond() {
		return second;
	}

	public void setSecond(List<String> second) {
		this.second = second;
	}

	public List<String> getThird() {
		return third;
	}

	public void setThird(List<String> third) {
		this.third = third;
	}

	public List<String> getFourth() {
		return fourth;
	}

	public void setFourth(List<String> fourth) {
		this.fourth = fourth;
	}

	public List<String> getFifth() {
		return fifth;
	}

	public void setFifth(List<String> fifth) {
		this.fifth = fifth;
	}

	public List<String> getSixth() {
		return sixth;
	}

	public void setSixth(List<String> sixth) {
		this.sixth = sixth;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

}
