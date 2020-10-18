/*
 * This file is generated by jOOQ.
 */
package com.ninjarmm.dao.jooq.tables.records;


import com.ninjarmm.dao.jooq.tables.CustomerService;
import com.ninjarmm.dao.jooq.tables.interfaces.ICustomerService;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "customer_service", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "customer_service_pk", columnNames = {"id"})
})
public class CustomerServiceRecord extends UpdatableRecordImpl<CustomerServiceRecord> implements Record5<UUID, UUID, UUID, String, OffsetDateTime>, ICustomerService {

    private static final long serialVersionUID = 913314832;

    /**
     * Setter for <code>public.customer_service.id</code>.
     */
    @Override
    public CustomerServiceRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.customer_service.id</code>.
     */
    @Id
    @Column(name = "id", nullable = false)
    @Override
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.customer_service.customerid</code>.
     */
    @Override
    public CustomerServiceRecord setCustomerid(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.customer_service.customerid</code>.
     */
    @Column(name = "customerid")
    @Override
    public UUID getCustomerid() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.customer_service.serviceid</code>.
     */
    @Override
    public CustomerServiceRecord setServiceid(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.customer_service.serviceid</code>.
     */
    @Column(name = "serviceid")
    @Override
    public UUID getServiceid() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.customer_service.lastModifiedBy</code>.
     */
    @Override
    public CustomerServiceRecord setLastmodifiedby(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.customer_service.lastModifiedBy</code>.
     */
    @Column(name = "lastModifiedBy")
    @Override
    public String getLastmodifiedby() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.customer_service.lastmodifieddatetime</code>.
     */
    @Override
    public CustomerServiceRecord setLastmodifieddatetime(OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.customer_service.lastmodifieddatetime</code>.
     */
    @Column(name = "lastmodifieddatetime")
    @Override
    public OffsetDateTime getLastmodifieddatetime() {
        return (OffsetDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, UUID, UUID, String, OffsetDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<UUID, UUID, UUID, String, OffsetDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return CustomerService.CUSTOMER_SERVICE.ID;
    }

    @Override
    public Field<UUID> field2() {
        return CustomerService.CUSTOMER_SERVICE.CUSTOMERID;
    }

    @Override
    public Field<UUID> field3() {
        return CustomerService.CUSTOMER_SERVICE.SERVICEID;
    }

    @Override
    public Field<String> field4() {
        return CustomerService.CUSTOMER_SERVICE.LASTMODIFIEDBY;
    }

    @Override
    public Field<OffsetDateTime> field5() {
        return CustomerService.CUSTOMER_SERVICE.LASTMODIFIEDDATETIME;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getCustomerid();
    }

    @Override
    public UUID component3() {
        return getServiceid();
    }

    @Override
    public String component4() {
        return getLastmodifiedby();
    }

    @Override
    public OffsetDateTime component5() {
        return getLastmodifieddatetime();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getCustomerid();
    }

    @Override
    public UUID value3() {
        return getServiceid();
    }

    @Override
    public String value4() {
        return getLastmodifiedby();
    }

    @Override
    public OffsetDateTime value5() {
        return getLastmodifieddatetime();
    }

    @Override
    public CustomerServiceRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerServiceRecord value2(UUID value) {
        setCustomerid(value);
        return this;
    }

    @Override
    public CustomerServiceRecord value3(UUID value) {
        setServiceid(value);
        return this;
    }

    @Override
    public CustomerServiceRecord value4(String value) {
        setLastmodifiedby(value);
        return this;
    }

    @Override
    public CustomerServiceRecord value5(OffsetDateTime value) {
        setLastmodifieddatetime(value);
        return this;
    }

    @Override
    public CustomerServiceRecord values(UUID value1, UUID value2, UUID value3, String value4, OffsetDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ICustomerService from) {
        setId(from.getId());
        setCustomerid(from.getCustomerid());
        setServiceid(from.getServiceid());
        setLastmodifiedby(from.getLastmodifiedby());
        setLastmodifieddatetime(from.getLastmodifieddatetime());
    }

    @Override
    public <E extends ICustomerService> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerServiceRecord
     */
    public CustomerServiceRecord() {
        super(CustomerService.CUSTOMER_SERVICE);
    }

    /**
     * Create a detached, initialised CustomerServiceRecord
     */
    public CustomerServiceRecord(UUID id, UUID customerid, UUID serviceid, String lastmodifiedby, OffsetDateTime lastmodifieddatetime) {
        super(CustomerService.CUSTOMER_SERVICE);

        set(0, id);
        set(1, customerid);
        set(2, serviceid);
        set(3, lastmodifiedby);
        set(4, lastmodifieddatetime);
    }
}