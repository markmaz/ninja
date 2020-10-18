/*
 * This file is generated by jOOQ.
 */
package com.ninjarmm.dao.jooq.tables.pojos;


import com.ninjarmm.dao.jooq.tables.interfaces.ICustomerService;

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
@Table(name = "customer_service", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "customer_service_pk", columnNames = {"id"})
})
public class CustomerService implements ICustomerService {

    private static final long serialVersionUID = -1374100409;

    private UUID           id;
    private UUID           customerid;
    private UUID           serviceid;
    private String         lastmodifiedby;
    private OffsetDateTime lastmodifieddatetime;

    public CustomerService() {}

    public CustomerService(ICustomerService value) {
        this.id = value.getId();
        this.customerid = value.getCustomerid();
        this.serviceid = value.getServiceid();
        this.lastmodifiedby = value.getLastmodifiedby();
        this.lastmodifieddatetime = value.getLastmodifieddatetime();
    }

    public CustomerService(
        UUID           id,
        UUID           customerid,
        UUID           serviceid,
        String         lastmodifiedby,
        OffsetDateTime lastmodifieddatetime
    ) {
        this.id = id;
        this.customerid = customerid;
        this.serviceid = serviceid;
        this.lastmodifiedby = lastmodifiedby;
        this.lastmodifieddatetime = lastmodifieddatetime;
    }

    @Id
    @Column(name = "id", nullable = false)
    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public CustomerService setId(UUID id) {
        this.id = id;
        return this;
    }

    @Column(name = "customerid")
    @Override
    public UUID getCustomerid() {
        return this.customerid;
    }

    @Override
    public CustomerService setCustomerid(UUID customerid) {
        this.customerid = customerid;
        return this;
    }

    @Column(name = "serviceid")
    @Override
    public UUID getServiceid() {
        return this.serviceid;
    }

    @Override
    public CustomerService setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
        return this;
    }

    @Column(name = "lastModifiedBy")
    @Override
    public String getLastmodifiedby() {
        return this.lastmodifiedby;
    }

    @Override
    public CustomerService setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
        return this;
    }

    @Column(name = "lastmodifieddatetime")
    @Override
    public OffsetDateTime getLastmodifieddatetime() {
        return this.lastmodifieddatetime;
    }

    @Override
    public CustomerService setLastmodifieddatetime(OffsetDateTime lastmodifieddatetime) {
        this.lastmodifieddatetime = lastmodifieddatetime;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CustomerService other = (CustomerService) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (customerid == null) {
            if (other.customerid != null)
                return false;
        }
        else if (!customerid.equals(other.customerid))
            return false;
        if (serviceid == null) {
            if (other.serviceid != null)
                return false;
        }
        else if (!serviceid.equals(other.serviceid))
            return false;
        if (lastmodifiedby == null) {
            if (other.lastmodifiedby != null)
                return false;
        }
        else if (!lastmodifiedby.equals(other.lastmodifiedby))
            return false;
        if (lastmodifieddatetime == null) {
            if (other.lastmodifieddatetime != null)
                return false;
        }
        else if (!lastmodifieddatetime.equals(other.lastmodifieddatetime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.customerid == null) ? 0 : this.customerid.hashCode());
        result = prime * result + ((this.serviceid == null) ? 0 : this.serviceid.hashCode());
        result = prime * result + ((this.lastmodifiedby == null) ? 0 : this.lastmodifiedby.hashCode());
        result = prime * result + ((this.lastmodifieddatetime == null) ? 0 : this.lastmodifieddatetime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerService (");

        sb.append(id);
        sb.append(", ").append(customerid);
        sb.append(", ").append(serviceid);
        sb.append(", ").append(lastmodifiedby);
        sb.append(", ").append(lastmodifieddatetime);

        sb.append(")");
        return sb.toString();
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
}
