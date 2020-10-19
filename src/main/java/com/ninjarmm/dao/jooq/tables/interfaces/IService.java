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
@Table(name = "service", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "service_pk", columnNames = {"id"})
})
public interface IService extends Serializable {

    /**
     * Setter for <code>public.service.id</code>.
     */
    public IService setId(UUID value);

    /**
     * Getter for <code>public.service.id</code>.
     */
    @Id
    @Column(name = "id", nullable = false)
    public UUID getId();

    /**
     * Setter for <code>public.service.servicename</code>.
     */
    public IService setServicename(String value);

    /**
     * Getter for <code>public.service.servicename</code>.
     */
    @Column(name = "servicename", nullable = false)
    public String getServicename();

    /**
     * Setter for <code>public.service.priceformac</code>.
     */
    public IService setPriceformac(BigDecimal value);

    /**
     * Getter for <code>public.service.priceformac</code>.
     */
    @Column(name = "priceformac", nullable = false)
    public BigDecimal getPriceformac();

    /**
     * Setter for <code>public.service.lastmodifiedby</code>.
     */
    public IService setLastmodifiedby(String value);

    /**
     * Getter for <code>public.service.lastmodifiedby</code>.
     */
    @Column(name = "lastmodifiedby")
    public String getLastmodifiedby();

    /**
     * Setter for <code>public.service.priceforwindows</code>.
     */
    public IService setPriceforwindows(BigDecimal value);

    /**
     * Getter for <code>public.service.priceforwindows</code>.
     */
    @Column(name = "priceforwindows")
    public BigDecimal getPriceforwindows();

    /**
     * Setter for <code>public.service.lastmodifieddatetime</code>.
     */
    public IService setLastmodifieddatetime(OffsetDateTime value);

    /**
     * Getter for <code>public.service.lastmodifieddatetime</code>.
     */
    @Column(name = "lastmodifieddatetime")
    public OffsetDateTime getLastmodifieddatetime();

    /**
     * Setter for <code>public.service.comments</code>.
     */
    public IService setComments(String value);

    /**
     * Getter for <code>public.service.comments</code>.
     */
    @Column(name = "comments")
    public String getComments();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IService
     */
    public void from(IService from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IService
     */
    public <E extends IService> E into(E into);
}
