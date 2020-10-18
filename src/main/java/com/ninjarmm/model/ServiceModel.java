package com.ninjarmm.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ServiceModel extends BaseModel<ServiceModel> {
    private String serviceName;
    private BigDecimal macPricing;
    private BigDecimal windowsPricing;
    private String comment;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getMacPricing() {
        return macPricing;
    }

    public void setMacPricing(BigDecimal macPricing) {
        this.macPricing = macPricing;
    }

    public BigDecimal getWindowsPricing() {
        return windowsPricing;
    }

    public void setWindowsPricing(BigDecimal windowsPricing) {
        this.windowsPricing = windowsPricing;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServiceModel that = (ServiceModel) o;
        return Objects.equals(serviceName, that.serviceName) &&
                Objects.equals(macPricing, that.macPricing) &&
                Objects.equals(windowsPricing, that.windowsPricing) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serviceName, macPricing, windowsPricing, comment);
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "serviceName='" + serviceName + '\'' +
                ", macPricing=" + macPricing +
                ", windowsPricing=" + windowsPricing +
                ", comment='" + comment + '\'' +
                '}';
    }
}
