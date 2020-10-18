package com.ninjarmm.dao;

import com.ninjarmm.dao.jooq.tables.pojos.Customer;
import com.ninjarmm.dao.jooq.tables.pojos.Service;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.PagedResults;

import java.util.List;
import java.util.UUID;

public interface ICustomerServiceDataService {
    PagedResults<Service> selectManyByCustomer(UUID customerId, int page, int size);
    List<Service> selectManyByCustomer(UUID customerId);

    PagedResults<Customer> selectManyByService(UUID serviceID, int page, int size);

    void createCustomerServiceRelationship(UUID customerId, UUID serviceId, User user);
    void deleteCustomerServiceRelationship(UUID customerId, UUID serviceId);

    boolean doesRelationExist(UUID customerId, UUID serviceID);
}
