package com.ninjarmm.services.impl;

import com.ninjarmm.dao.IUserDataService;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IMapperService;
import com.ninjarmm.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final IUserDataService userDataService;
    private final IMapperService mapperService;

    public UserService(final IUserDataService userDataService, final IMapperService mapperService){
        this.userDataService = userDataService;
        this.mapperService = mapperService;
    }

    @Override
    public UserModel getOne(UUID id) {
        return null;
    }

    @Override
    public PagedResults<UserModel> getMany(int page, int size) {
        var results = userDataService.selectMany(page, size);
        return  mapperService.toModel(results, page, size, UserModel.class);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public UserModel update(UserModel resource, UserModel userModel) {
        return null;
    }

    @Override
    public UserModel add(UserModel resource, UserModel userModel) {
        var result = this.userDataService.insert(mapperService.toDao(resource), mapperService.toDao(userModel));
        return mapperService.toModel(result);
    }

    @Override
    public UserModel findByUserName(String username) {
        return mapperService.toModel(this.userDataService.findByUsername(username));
    }
}
