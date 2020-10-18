package com.ninjarmm.services;

import com.ninjarmm.model.Bill;
import com.ninjarmm.model.CustomerModel;

import java.util.UUID;

public interface ICustomerService extends IResource<CustomerModel> {
    Bill generateBill(UUID customerId);
}
