package com.ninjarmm.dao.impl;

import com.ninjarmm.dao.IDeviceDataService;
import com.ninjarmm.dao.jooq.tables.pojos.Device;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.exceptions.InternalServerException;
import com.ninjarmm.exceptions.NotFoundException;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.utils.DBUtils;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static com.ninjarmm.dao.jooq.Tables.CUSTOMER;
import static com.ninjarmm.dao.jooq.Tables.DEVICE;

@Service
public class DeviceDataService implements IDeviceDataService {
    private final DSLContext context;

    public DeviceDataService(DSLContext context){
        this.context = context;
    }

    @Override
    public Device selectOne(UUID id, UUID parentId) {

        var result = context.select(DEVICE.asterisk()).from(DEVICE).where(DEVICE.ID.eq(id)).and(DEVICE.CUSTOMERID.eq(parentId)).fetchOne();

        if(result == null){
            throw new NotFoundException("This device does not exist.");
        }

        return result.into(Device.class);
    }

    @Override
    public void delete(UUID id) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).delete(DEVICE).where(DEVICE.ID.eq(id)).execute();
            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });
    }

    @Override
    public Device update(Device resource, User user) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).update(DEVICE)
                        .set(DEVICE.DEVICETYPE, resource.getDevicetype())
                        .set(DEVICE.CUSTOMERID, resource.getCustomerid())
                        .set(DEVICE.SYSTEMNAME, resource.getSystemname())
                        .set(CUSTOMER.LASTMODIFIEDBY, user.getUsername())
                        .set(DEVICE.DEVICECOST, resource.getDevicecost())
                        .set(CUSTOMER.LASTMODIFIEDDATETIME, OffsetDateTime.now()).where(DEVICE.ID.eq(resource.getId())).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(resource.getId(), resource.getCustomerid());
    }

    @Override
    public Device insert(Device resource, User user) {
        var newID = UUID.randomUUID();

        context.transaction(configuration -> {
            try {
                DSL.using(configuration).insertInto(DEVICE, DEVICE.ID, DEVICE.DEVICETYPE, DEVICE.SYSTEMNAME, DEVICE.CUSTOMERID, DEVICE.DEVICECOST, DEVICE.LASTMODIFIEDBY, DEVICE.LASTMODIFIEDDATETIME)
                        .values(newID, resource.getDevicetype(), resource.getSystemname(), resource.getCustomerid(), resource.getDevicecost(), user.getUsername(), OffsetDateTime.now()).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(newID, resource.getCustomerid());

    }

    @Override
    public PagedResults<Device> selectManyByParentId(UUID parentId, int page, int size) {
        var result = context.select(DEVICE.asterisk(), DBUtils.getCountOverField()).from(DEVICE).where(DEVICE.CUSTOMERID.eq(parentId)).limit(size).offset(page * size).fetch();
        return PagedResults.forResult(result, Device.class, size, page);
    }

    @Override
    public List<Device> selectManyByParentId(UUID parentId) {
        var result = context.select(DEVICE.asterisk(), DBUtils.getCountOverField()).from(DEVICE).where(DEVICE.CUSTOMERID.eq(parentId)).fetch();
        return result.into(Device.class);
    }
}
