package com.xsw.common.entry;

import java.io.Serializable;

public class SysOperationValue implements Serializable {
	private static final long serialVersionUID = 8017935406318721976L;

	private String id;
	private String name;
	private String title;
	private String createTime;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysOperationValue [id=" + id + ", name=" + name + ", title=" + title + ", createTime=" + createTime
				+ "]";
	}

}
