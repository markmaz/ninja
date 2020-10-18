package com.ninjarmm.services.impl;

import com.ninjarmm.dao.IDeviceDataService;
import com.ninjarmm.exceptions.UnprocessableEntityException;
import com.ninjarmm.model.DeviceModel;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IDeviceService;
import com.ninjarmm.services.IMapperService;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.UUID;

@Service
public class DeviceService extends BaseService<DeviceModel> implements IDeviceService {
    private final IDeviceDataService dataService;
    private final IMapperService mapperService;

    public DeviceService(final IDeviceDataService dataService, final IMapperService mapperService, final Validator validator){
        super(validator);

        this.dataService = dataService;
        this.mapperService = mapperService;
    }


    @Override
    public DeviceModel getOne(UUID id, UUID customerId) {
        var results = dataService.selectOne(id, customerId);
        return mapperService.toModel(results);
    }

    @Override
    public PagedResults<DeviceModel> getManyByCustomer(UUID customerId, int page, int size) {
        var results = dataService.selectManyByParentId(customerId, page, size);
        return mapperService.toModel(results, page, size, DeviceModel.class);
    }

    @Override
    public void delete(UUID id) {
        dataService.delete(id);
    }

    @Override
    public DeviceModel update(DeviceModel resource, UUID customerId, UserModel userModel) {
        validate(resource);

        var device = mapperService.toDao(resource);
        device.setCustomerid(customerId);

        var results =  dataService.update(device, mapperService.toDao(userModel));
        return mapperService.toModel(results);
    }

    @Override
    public DeviceModel add(DeviceModel resource, UUID customerId, UserModel userModel) {
        validate(resource);

        if(resource.getDeviceType() == null){
            throw new UnprocessableEntityException("deviceType can not be null");
        }

        var device = mapperService.toDao(resource);
        device.setCustomerid(customerId);

        var results =  dataService.insert(device, mapperService.toDao(userModel));
        return mapperService.toModel(results);
    }
}
