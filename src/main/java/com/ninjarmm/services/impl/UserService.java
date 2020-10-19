package com.ninjarmm.services.impl;

import com.ninjarmm.dao.IUserDataService;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IMapperService;
import com.ninjarmm.services.IUserService;
import org.springframework.stereotype.Service;

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
        return mapperService.toModel(userDataService.selectOne(id));
    }

    @Override
    public PagedResults<UserModel> getMany(int page, int size) {
        var results = userDataService.selectMany(page, size);
        return  mapperService.toModel(results, page, size, UserModel.class);
    }

    @Override
    public void delete(UUID id) {
        userDataService.delete(id);
    }

    @Override
    public UserModel update(UserModel resource, UserModel userModel) {
        if(resource.getId() != null){
            var results = userDataService.update(mapperService.toDao(resource), mapperService.toDao(userModel));
            return mapperService.toModel(results);
        }else{
            var results = userDataService.insert(mapperService.toDao(resource), mapperService.toDao(userModel));
            return mapperService.toModel(results);
        }
    }

    @Override
    public UserModel add(UserModel resource, UserModel userModel) {
        var result = this.userDataService.insert(mapperService.toDao(resource), mapperService.toDao(userModel));
        return mapperService.toModel(result);
    }

    @Override
    public UserModel findByUserName(String username) {
        var results = userDataService.findByUsername(username);
        return mapperService.toModel(results);
    }
}
