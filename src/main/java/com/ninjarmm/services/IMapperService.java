package com.ninjarmm.services;

import com.ninjarmm.dao.jooq.tables.pojos.Customer;
import com.ninjarmm.dao.jooq.tables.pojos.Device;
import com.ninjarmm.dao.jooq.tables.pojos.Service;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.*;

import java.util.List;

public interface IMapperService {
    User toDao(UserModel userModel);
    UserModel toModel(User user);

    Customer toDao(CustomerModel customerModel);
    CustomerModel toModel(Customer  customer);

    Device toDao(DeviceModel deviceModel);
    DeviceModel toModel(Device device);

    Service toDao(ServiceModel serviceModel);
    ServiceModel toModel(Service service);

    <T, R> PagedResults<R> toModel(PagedResults<T> result, int page, int size, java.lang.Class<? extends R> arg);

    <T, R> List<R> toModel(List<T> result, java.lang.Class<? extends R> arg);
}
