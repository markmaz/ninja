package com.ninjarmm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class DeviceModel extends BaseModel<DeviceModel>{
    @NotEmpty(message = "The system name can not be empty")
    @JsonProperty("systemName")
    private String systemName;

    @JsonProperty("deviceType")
    private DeviceType deviceType;

    private BigDecimal deviceCost = new BigDecimal(4);

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public BigDecimal getDeviceCost() {
        return deviceCost;
    }

    public void setDeviceCost(BigDecimal deviceCost) {
        this.deviceCost = deviceCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceModel that = (DeviceModel) o;
        return Objects.equals(systemName, that.systemName) &&
                deviceType == that.deviceType &&
                Objects.equals(deviceCost, that.deviceCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), systemName, deviceType, deviceCost);
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "systemName='" + systemName + '\'' +
                ", deviceType=" + deviceType +
                ", deviceCost=" + deviceCost +
                '}';
    }
}
