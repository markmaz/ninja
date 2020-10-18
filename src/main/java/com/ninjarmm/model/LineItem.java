package com.ninjarmm.model;

import java.math.BigDecimal;
import java.util.Objects;

public class LineItem {
    String serviceName;
    int deviceCount;
    int macCount;
    int windowsCount;
    BigDecimal lineTotal;

    public LineItem withServiceName(String name){
        this.serviceName = name;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LineItem withDeviceCount(int count){
        this.deviceCount = count;
        return this;
    }
    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public LineItem withMacCount(int count){
        this.macCount = count;
        return this;
    }

    public int getMacCount() {
        return macCount;
    }

    public void setMacCount(int macCount) {
        this.macCount = macCount;
    }

    public LineItem withWindowsCount(int count){
        this.windowsCount = count;
        return this;
    }

    public int getWindowsCount() {
        return windowsCount;
    }

    public void setWindowsCount(int windowsCount) {
        this.windowsCount = windowsCount;
    }

    public LineItem withLineTotal(BigDecimal decimal){
        this.lineTotal = decimal;
        return this;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return deviceCount == lineItem.deviceCount &&
                macCount == lineItem.macCount &&
                windowsCount == lineItem.windowsCount &&
                Objects.equals(serviceName, lineItem.serviceName) &&
                Objects.equals(lineTotal, lineItem.lineTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, deviceCount, macCount, windowsCount, lineTotal);
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "serviceName='" + serviceName + '\'' +
                ", deviceCount=" + deviceCount +
                ", macCount=" + macCount +
                ", windowsCount=" + windowsCount +
                ", lineTotal=" + lineTotal +
                '}';
    }
}