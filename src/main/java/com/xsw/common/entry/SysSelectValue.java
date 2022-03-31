package com.xsw.common.entry;

import java.io.Serializable;

public class SysSelectValue implements Serializable {
    private static final long serialVersionUID = -8868371053290102690L;

    private Integer rowNumMin;
    private Integer rowNumMax;
    private String name;
    private String startTime;
    private String endTime;

    public Integer getRowNumMin() {
        return rowNumMin;
    }

    public void setRowNumMin(Integer rowNumMin) {
        this.rowNumMin = rowNumMin;
    }

    public Integer getRowNumMax() {
        return rowNumMax;
    }

    public void setRowNumMax(Integer rowNumMax) {
        this.rowNumMax = rowNumMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "SysSelectValue [rowNumMin=" + rowNumMin + ", rowNumMax=" + rowNumMax + ", name=" + name + ", startTime="
                + startTime + ", endTime=" + endTime + "]";
    }

}
