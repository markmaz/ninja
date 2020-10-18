package com.ninjarmm.dao.impl;

import com.ninjarmm.dao.IServiceDataService;
import com.ninjarmm.dao.jooq.tables.pojos.Service;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.exceptions.InternalServerException;
import com.ninjarmm.exceptions.NotFoundException;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.utils.DBUtils;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.ninjarmm.dao.jooq.Tables.*;

@org.springframework.stereotype.Service
public class ServicesDataService implements IServiceDataService {
    private final DSLContext context;

    public ServicesDataService(DSLContext context){
        this.context = context;
    }

    @Override
    public Service selectOne(UUID id) {
        var result = context.select(SERVICE.asterisk()).from(SERVICE).where(SERVICE.ID.eq(id)).fetchOne();

        if(result == null){
            throw new NotFoundException("This device does not exist.");
        }

        return result.into(Service.class);
    }

    @Override
    public PagedResults<Service> selectMany(int page, int size) {
        var result = context.select(SERVICE.asterisk(), DBUtils.getCountOverField()).from(SERVICE).limit(size).offset(page * size).fetch();
        return PagedResults.forResult(result, Service.class, size, page);
    }

    @Override
    public void delete(UUID id) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).delete(SERVICE).where(SERVICE.ID.eq(id)).execute();
            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });
    }

    @Override
    public Service update(Service resource, User user) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).update(DEVICE)
                        .set(SERVICE.COMMENTS, resource.getComments())
                        .set(SERVICE.SERVICENAME, resource.getServicename())
                        .set(SERVICE.PRICEFORMAC, resource.getPriceformac())
                        .set(SERVICE.PRICEFORWINDOWS, resource.getPriceforwindows())
                        .set(SERVICE.LASTMODIFIEDBY, user.getUsername())
                        .set(SERVICE.LASTMODIFIEDDATETIME, OffsetDateTime.now()).where(SERVICE.ID.eq(resource.getId())).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(resource.getId());
    }

    @Override
    public Service insert(Service resource, User user) {
        var newID = UUID.randomUUID();

        context.transaction(configuration -> {
            try {
                DSL.using(configuration).insertInto(SERVICE, SERVICE.ID, SERVICE.SERVICENAME, SERVICE.COMMENTS, SERVICE.PRICEFORMAC, SERVICE.PRICEFORWINDOWS,  SERVICE.LASTMODIFIEDBY, SERVICE.LASTMODIFIEDDATETIME)
                        .values(newID, resource.getServicename(), resource.getComments(), resource.getPriceformac(), resource.getPriceforwindows(), user.getUsername(), OffsetDateTime.now()).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(newID);
    }
}
