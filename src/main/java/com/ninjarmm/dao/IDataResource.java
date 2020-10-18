package com.ninjarmm.dao;


import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.PagedResults;

import java.util.UUID;

public interface IDataResource<T> {
    T selectOne(UUID id);
    PagedResults<T> selectMany(int page, int size);
    void delete(UUID id);
    T update(T resource, User user);
    T insert(T resource, User user);
}
