package com.ninjarmm.dao;

import com.ninjarmm.dao.jooq.tables.pojos.Device;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.PagedResults;

import java.util.List;
import java.util.UUID;

public interface IDeviceDataService {
    PagedResults<Device> selectManyByParentId(UUID parentId, int page, int size);
    List<Device> selectManyByParentId(UUID parentId);
    Device selectOne(UUID id, UUID parentId);
    void delete(UUID id);
    Device update(Device resource, User user);
    Device insert(Device resource, User user);
}
