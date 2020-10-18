package com.ninjarmm.dao.impl;


import com.ninjarmm.dao.IDataResource;
import com.ninjarmm.dao.jooq.tables.pojos.Customer;
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

@Service
public class CustomerDataService implements IDataResource<Customer> {

    private final DSLContext context;

    public CustomerDataService(DSLContext context){
        this.context = context;
    }

    @Override
    public Customer selectOne(UUID id) {
       var result = context.select(CUSTOMER.asterisk()).from(CUSTOMER).where(CUSTOMER.ID.eq(id)).fetchOne();

       if(result == null){
           throw new NotFoundException("This customer does not exist.");
       }

       return result.into(Customer.class);
    }

    @Override
    public PagedResults<Customer> selectMany(int page, int size) {
        var result =  context.select(CUSTOMER.asterisk(), DBUtils.getCountOverField()).from(CUSTOMER).limit(size).offset(page * size).fetch();
        return PagedResults.forResult(result, Customer.class, size, page);
    }

    @Override
    public void delete(UUID id) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).delete(CUSTOMER).where(CUSTOMER.ID.eq(id)).execute();
            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });
    }

    @Override
    public Customer update(Customer resource, User user) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).update(CUSTOMER)
                        .set(CUSTOMER.NAME, resource.getName())
                        .set(CUSTOMER.LASTMODIFIEDBY, user.getUsername())
                        .set(CUSTOMER.LASTMODIFIEDDATETIME, OffsetDateTime.now()).where(CUSTOMER.ID.eq(resource.getId())).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(resource.getId());
    }

    @Override
    public Customer insert(Customer resource, User user) {
        var newID = UUID.randomUUID();

        context.transaction(configuration -> {
            try {
                DSL.using(configuration).insertInto(CUSTOMER, CUSTOMER.ID, CUSTOMER.NAME, CUSTOMER.LASTMODIFIEDBY, CUSTOMER.LASTMODIFIEDDATETIME)
                        .values(newID, resource.getName(), user.getUsername(), OffsetDateTime.now()).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });

        return selectOne(newID);
    }
}
