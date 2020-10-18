package com.ninjarmm.dao;

import com.ninjarmm.dao.jooq.tables.pojos.Service;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.PagedResults;

import java.util.UUID;

public interface IServiceDataService {
    Service selectOne(UUID id);
    PagedResults<Service> selectMany(int page, int size);
    void delete(UUID id);
    Service update(Service resource, User user);
    Service insert(Service resource, User user);
}
