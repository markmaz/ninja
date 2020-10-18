package com.ninjarmm.services;

import com.ninjarmm.model.UserModel;

public interface IUserService extends IResource<UserModel> {
    UserModel findByUserName(String username);
}
