package com.ninjarmm.services.impl;

import com.ninjarmm.dao.ICustomerServiceDataService;
import com.ninjarmm.exceptions.ConflictException;
import com.ninjarmm.model.CustomerModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.ServiceModel;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.ICustomerServiceService;
import com.ninjarmm.services.IMapperService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceService implements ICustomerServiceService {
    private final ICustomerServiceDataService dataService;
    private final IMapperService mapperService;

    public CustomerServiceService(final ICustomerServiceDataService dataService, final IMapperService mapperService){
        this.dataService = dataService;
        this.mapperService = mapperService;
    }

    @Override
    public void addServiceToCustomer(UUID customerId, UUID serviceId, UserModel user) {
        if(dataService.doesRelationExist(customerId, serviceId)){
            throw new ConflictException("Customer already has this service");
        }else{
            dataService.createCustomerServiceRelationship(customerId, serviceId, mapperService.toDao(user));
        }
    }

    @Override
    public void removeServiceFromCustomer(UUID customerId, UUID serviceId) {
        dataService.deleteCustomerServiceRelationship(customerId, serviceId);
    }

    @Override
    public PagedResults<ServiceModel> getAllServicesForCustomer(UUID customerId, int page, int size) {
        var results = dataService.selectManyByCustomer(customerId, page, size);
        return mapperService.toModel(results, page, size, ServiceModel.class);
    }

    @Override
    public PagedResults<CustomerModel> getAllCustomersForService(UUID serviceId, int page, int size) {
        var results = dataService.selectManyByService(serviceId, page, size);
        return mapperService.toModel(results, page, size, CustomerModel.class);
    }
}
