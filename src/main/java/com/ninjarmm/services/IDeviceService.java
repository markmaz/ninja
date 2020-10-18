package com.ninjarmm.services;

import com.ninjarmm.model.DeviceModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;

import java.util.UUID;

public interface IDeviceService {
    DeviceModel getOne(UUID id, UUID customerId);
    PagedResults<DeviceModel> getManyByCustomer(UUID customerId, int page, int size);
    void delete(UUID id);
    DeviceModel update(DeviceModel resource, UUID customerId, UserModel userModel);
    DeviceModel add(DeviceModel resource, UUID customerId, UserModel userModel);
}
