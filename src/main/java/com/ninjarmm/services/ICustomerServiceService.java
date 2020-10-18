package com.ninjarmm.services;

import com.ninjarmm.model.CustomerModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.ServiceModel;
import com.ninjarmm.model.UserModel;

import java.util.UUID;

public interface ICustomerServiceService {
    void addServiceToCustomer(UUID customerId, UUID serviceId, UserModel user);
    void removeServiceFromCustomer(UUID customerId, UUID serviceId);
    PagedResults<ServiceModel> getAllServicesForCustomer(UUID customerId, int page, int size);
    PagedResults<CustomerModel> getAllCustomersForService(UUID serviceId, int page, int size);
}
