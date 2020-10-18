package com.ninjarmm.dao;

import com.ninjarmm.dao.jooq.tables.pojos.User;

public interface IUserDataService extends IDataResource<User> {
    User findByUsername(String username);
}
