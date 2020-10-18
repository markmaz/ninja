package com.ninjarmm.dao.impl;

import com.ninjarmm.dao.ICustomerServiceDataService;
import com.ninjarmm.dao.jooq.tables.pojos.Customer;
import com.ninjarmm.dao.jooq.tables.pojos.Service;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.exceptions.InternalServerException;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.utils.DBUtils;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static com.ninjarmm.dao.jooq.Tables.*;

@org.springframework.stereotype.Service
public class CustomerServiceDataService implements ICustomerServiceDataService {
    private final DSLContext context;

    public CustomerServiceDataService(final DSLContext context){
        this.context = context;
    }

    @Override
    public PagedResults<Service> selectManyByCustomer(UUID customerId, int page, int size) {
        var result = context.select(SERVICE.asterisk(), DBUtils.getCountOverField()).from(SERVICE)
                .join(CUSTOMER_SERVICE).on(CUSTOMER_SERVICE.SERVICEID.eq(SERVICE.ID))
                .and(CUSTOMER_SERVICE.CUSTOMERID.eq(customerId)).limit(size).offset(page * size).fetch();
        return PagedResults.forResult(result, Service.class, size, page);
    }

    @Override
    public List<Service> selectManyByCustomer(UUID customerId) {
        var result = context.select(SERVICE.asterisk(), DBUtils.getCountOverField()).from(SERVICE)
                .join(CUSTOMER_SERVICE).on(CUSTOMER_SERVICE.SERVICEID.eq(SERVICE.ID))
                .and(CUSTOMER_SERVICE.CUSTOMERID.eq(customerId)).fetch();

        return result.into(Service.class);
    }


    @Override
    public PagedResults<Customer> selectManyByService(UUID serviceID, int page, int size) {
        var result = context.select(CUSTOMER.asterisk(), DBUtils.getCountOverField()).from(CUSTOMER)
                .join(CUSTOMER_SERVICE).on(CUSTOMER_SERVICE.CUSTOMERID.eq(CUSTOMER.ID))
                .and(CUSTOMER_SERVICE.SERVICEID.eq(serviceID)).limit(size).offset(page * size).fetch();
        return PagedResults.forResult(result, Customer.class, size, page);
    }

    @Override
    public void createCustomerServiceRelationship(UUID customerId, UUID serviceId, User user) {
        var newID = UUID.randomUUID();

        context.transaction(configuration -> {
            try {
                DSL.using(configuration).insertInto(CUSTOMER_SERVICE, CUSTOMER_SERVICE.ID, CUSTOMER_SERVICE.CUSTOMERID, CUSTOMER_SERVICE.SERVICEID, CUSTOMER_SERVICE.LASTMODIFIEDBY, CUSTOMER_SERVICE.LASTMODIFIEDDATETIME)
                        .values(newID, customerId, serviceId, user.getUsername(), OffsetDateTime.now()).execute();

            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });
    }

    @Override
    public void deleteCustomerServiceRelationship(UUID customerId, UUID serviceId) {
        context.transaction(configuration -> {
            try {
                DSL.using(configuration).delete(CUSTOMER_SERVICE).where(CUSTOMER_SERVICE.ID.eq(serviceId)).and(CUSTOMER_SERVICE.CUSTOMERID.eq(customerId)).execute();
            }catch (RuntimeException e){
                throw new InternalServerException(e.getMessage());
            }
        });
    }

    @Override
    public boolean doesRelationExist(UUID customerId, UUID serviceID) {
        var count = context.selectCount().from(CUSTOMER_SERVICE).where(CUSTOMER_SERVICE.CUSTOMERID.eq(customerId)).and(CUSTOMER_SERVICE.SERVICEID.eq(serviceID)).fetchOne(0, int.class);

        if (count > 0) {
            return true;
        }else{
            return false;
        }
    }
}
