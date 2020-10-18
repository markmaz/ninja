package com.ninjarmm.dao.impl;

import com.ninjarmm.dao.IUserDataService;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.exceptions.InternalServerException;
import com.ninjarmm.exceptions.NotFoundException;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.utils.DBUtils;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.ninjarmm.dao.jooq.Tables.CUSTOMER;
import static com.ninjarmm.dao.jooq.Tables.USER;

@Service
public class UserDataService implements IUserDataService {

    private final DSLContext context;

    public UserDataService(DSLContext context){
        this.context = context;
    }


    @Override
    public User selectOne(UUID id) {

        var result = context.select(USER.asterisk()).from(USER).where(USER.ID.eq(id)).fetchOne();

        if(result == null){
            throw new NotFoundException("This customer does not exist.");
        }

        return result.into(User.class);
    }

    @Override
    public PagedResults<User> selectMany(int page, int size) {
        var result =  context.select(USER.asterisk(), DBUtils.getCountOverField()).from(USER).limit(size).offset(page * size).fetch();
        return PagedResults.forResult(result, User.class, size, page);
    }

    @Override
    public void delete(UUID id) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).delete(USER).where(USER.ID.eq(id)).execute();
            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });
    }

    @Override
    public User update(User resource, User user) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).update(USER)
                        .set(USER.USERNAME, resource.getUsername())
                        .set(USER.PASSWORD, resource.getPassword())
                        .set(USER.LASTMODIFIEDBY, user.getUsername())
                        .set(CUSTOMER.LASTMODIFIEDDATETIME, OffsetDateTime.now()).where(USER.ID.eq(resource.getId())).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(resource.getId());
    }

    @Override
    public User insert(User resource, User user) {
        var newID = UUID.randomUUID();

        context.transaction(configuration -> {
            try {
                DSL.using(configuration).insertInto(USER, USER.ID, USER.USERNAME, USER.PASSWORD, USER.LASTMODIFIEDBY, USER.LASTMODIFIEDDATETIME)
                        .values(newID, resource.getUsername(), resource.getPassword(), user.getUsername(), OffsetDateTime.now()).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(newID);
    }

    @Override
    public User findByUsername(String username) {
        var result = context.select(USER.asterisk()).from(USER).where(USER.USERNAME.eq(username)).fetchOne();

        if(result == null){
            throw new NotFoundException("This customer does not exist.");
        }

        return result.into(User.class);
    }
}
