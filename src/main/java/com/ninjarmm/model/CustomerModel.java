package com.ninjarmm.model;

import java.util.Objects;

public class CustomerModel extends BaseModel<CustomerModel> {
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerModel that = (CustomerModel) o;
        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName);
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "customerName='" + customerName + '\'' +
                '}';
    }
}
