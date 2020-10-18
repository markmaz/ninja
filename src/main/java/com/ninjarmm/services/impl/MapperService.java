package com.ninjarmm.services.impl;

import com.ninjarmm.dao.jooq.tables.pojos.Customer;
import com.ninjarmm.dao.jooq.tables.pojos.Device;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.*;
import com.ninjarmm.services.IMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperService implements IMapperService {
    static ModelMapper mapper;

    private ModelMapper get()
    {
        if (null != mapper)
            return mapper;

        mapper = MappingBuilder.Build();
        return mapper;

    }

    @Override
    public User toDao(UserModel userModel) {
        return get().map(userModel, User.class);
    }

    @Override
    public UserModel toModel(User user) {
        return get().map(user, UserModel.class);
    }

    @Override
    public Customer toDao(CustomerModel customerModel) {
        return get().map(customerModel, Customer.class);
    }

    @Override
    public CustomerModel toModel(Customer customer) {
        return get().map(customer, CustomerModel.class);
    }

    @Override
    public Device toDao(DeviceModel deviceModel) {
        return get().map(deviceModel, Device.class);
    }

    @Override
    public DeviceModel toModel(Device device) {
        return get().map(device, DeviceModel.class);
    }

    @Override
    public com.ninjarmm.dao.jooq.tables.pojos.Service toDao(ServiceModel serviceModel) {
        return get().map(serviceModel, com.ninjarmm.dao.jooq.tables.pojos.Service.class);
    }

    @Override
    public ServiceModel toModel(com.ninjarmm.dao.jooq.tables.pojos.Service service) {
        return get().map(service, ServiceModel.class);
    }


    private <T, R> List<R> getItems(PagedResults<T> result, Class<? extends R> arg)
    {
        var mapper = get();
        var mapped = result.getValues().stream().map(item -> mapper.map(item, arg));
        return mapped.collect(Collectors.toList());
    }

    private <T, R> List<R> getItems(List<T> result, Class<? extends R> arg)
    {
        var mapper = get();
        var mapped = result.stream().map(item -> mapper.map(item, arg));
        return mapped.collect(Collectors.toList());
    }

    @Override
    public <T, R> PagedResults<R> toModel(PagedResults<T> result, int page, int size, java.lang.Class<? extends R> arg)
    {
        List<R> items = getItems(result, arg);
        return PagedResults.of(items, result.getCount(), page, size);
    }

    @Override
    public <T, R> List<R> toModel(List<T> result, java.lang.Class<? extends R> arg) {
        return getItems(result, arg);
    }
}
