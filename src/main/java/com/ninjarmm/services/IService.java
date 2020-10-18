package com.ninjarmm.services;

import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.ServiceModel;
import com.ninjarmm.model.UserModel;

import java.util.UUID;

public interface IService {
    ServiceModel getOne(UUID id);
    PagedResults<ServiceModel> getMany(int page, int size);
    void delete(UUID id);
    ServiceModel update(ServiceModel resource, UserModel user);
    ServiceModel add(ServiceModel resource, UserModel user);
}
