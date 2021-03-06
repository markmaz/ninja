/*
 * This file is generated by jOOQ.
 */
package com.ninjarmm.dao.jooq.tables.interfaces;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(name = "device", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "device_pk", columnNames = {"id"})
})
public interface IDevice extends Serializable {

    /**
     * Setter for <code>public.device.id</code>.
     */
    public IDevice setId(UUID value);

    /**
     * Getter for <code>public.device.id</code>.
     */
    @Id
    @Column(name = "id", nullable = false)
    public UUID getId();

    /**
     * Setter for <code>public.device.systemname</code>.
     */
    public IDevice setSystemname(String value);

    /**
     * Getter for <code>public.device.systemname</code>.
     */
    @Column(name = "systemname")
    public String getSystemname();

    /**
     * Setter for <code>public.device.devicetype</code>.
     */
    public IDevice setDevicetype(String value);

    /**
     * Getter for <code>public.device.devicetype</code>.
     */
    @Column(name = "devicetype")
    public String getDevicetype();

    /**
     * Setter for <code>public.device.customerid</code>.
     */
    public IDevice setCustomerid(UUID value);

    /**
     * Getter for <code>public.device.customerid</code>.
     */
    @Column(name = "customerid")
    public UUID getCustomerid();

    /**
     * Setter for <code>public.device.lastmodifieddatetime</code>.
     */
    public IDevice setLastmodifieddatetime(OffsetDateTime value);

    /**
     * Getter for <code>public.device.lastmodifieddatetime</code>.
     */
    @Column(name = "lastmodifieddatetime")
    public OffsetDateTime getLastmodifieddatetime();

    /**
     * Setter for <code>public.device.devicecost</code>.
     */
    public IDevice setDevicecost(BigDecimal value);

    /**
     * Getter for <code>public.device.devicecost</code>.
     */
    @Column(name = "devicecost")
    public BigDecimal getDevicecost();

    /**
     * Setter for <code>public.device.lastmodifiedby</code>.
     */
    public IDevice setLastmodifiedby(String value);

    /**
     * Getter for <code>public.device.lastmodifiedby</code>.
     */
    @Column(name = "lastmodifiedby")
    public String getLastmodifiedby();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IDevice
     */
    public void from(IDevice from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IDevice
     */
    public <E extends IDevice> E into(E into);
}
