package com.ninjarmm.services;

import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;

import java.util.UUID;

public interface IResource<T> {
    T getOne(UUID id);
    PagedResults<T> getMany(int page, int size);
    void delete(UUID id);
    T update(T resource, UserModel userModel);
    T add(T resource, UserModel userModel);
}
