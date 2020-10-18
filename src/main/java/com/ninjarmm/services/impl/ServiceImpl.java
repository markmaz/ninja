package com.ninjarmm.services.impl;

import com.ninjarmm.dao.IServiceDataService;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.ServiceModel;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IMapperService;
import com.ninjarmm.services.IService;

import javax.validation.Validator;
import java.util.UUID;

@org.springframework.stereotype.Service
public class ServiceImpl extends BaseService<ServiceModel> implements IService {
    private final IServiceDataService serviceDataService;
    private final IMapperService mapperService;

    public ServiceImpl(IServiceDataService serviceDataService, final IMapperService mapperService, final Validator validator){
        super(validator);
        this.mapperService = mapperService;
        this.serviceDataService = serviceDataService;
    }

    @Override
    public ServiceModel getOne(UUID id) {
        var result = serviceDataService.selectOne(id);
        return mapperService.toModel(result);
    }

    @Override
    public PagedResults<ServiceModel> getMany(int page, int size) {
        var results = serviceDataService.selectMany(page, size);
        return mapperService.toModel(results, page, size, ServiceModel.class);
    }

    @Override
    public void delete(UUID id) {
        serviceDataService.delete(id);
    }

    @Override
    public ServiceModel update(ServiceModel resource, UserModel user) {
        validate(resource);
        var result = serviceDataService.update(mapperService.toDao(resource), mapperService.toDao(user));
        return mapperService.toModel(result);
    }

    @Override
    public ServiceModel add(ServiceModel resource, UserModel user) {
        validate(resource);
        var result = serviceDataService.insert(mapperService.toDao(resource), mapperService.toDao(user));
        return mapperService.toModel(result);
    }
}
