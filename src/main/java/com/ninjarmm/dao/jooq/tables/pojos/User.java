/*
 * This file is generated by jOOQ.
 */
package com.ninjarmm.dao.jooq.tables.pojos;


import com.ninjarmm.dao.jooq.tables.interfaces.IUser;

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
@Table(name = "user", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "user_pkey", columnNames = {"id"})
})
public class User implements IUser {

    private static final long serialVersionUID = 1456796599;

    private UUID           id;
    private String         username;
    private String         password;
    private String         lastmodifiedby;
    private OffsetDateTime lastmodifieddatetime;

    public User() {}

    public User(IUser value) {
        this.id = value.getId();
        this.username = value.getUsername();
        this.password = value.getPassword();
        this.lastmodifiedby = value.getLastmodifiedby();
        this.lastmodifieddatetime = value.getLastmodifieddatetime();
    }

    public User(
        UUID           id,
        String         username,
        String         password,
        String         lastmodifiedby,
        OffsetDateTime lastmodifieddatetime
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
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
    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    @Column(name = "username", nullable = false)
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password", nullable = false)
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "lastModifiedBy")
    @Override
    public String getLastmodifiedby() {
        return this.lastmodifiedby;
    }

    @Override
    public User setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
        return this;
    }

    @Column(name = "lastmodifieddatetime")
    @Override
    public OffsetDateTime getLastmodifieddatetime() {
        return this.lastmodifieddatetime;
    }

    @Override
    public User setLastmodifieddatetime(OffsetDateTime lastmodifieddatetime) {
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
        final User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
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
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.lastmodifiedby == null) ? 0 : this.lastmodifiedby.hashCode());
        result = prime * result + ((this.lastmodifieddatetime == null) ? 0 : this.lastmodifieddatetime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(lastmodifiedby);
        sb.append(", ").append(lastmodifieddatetime);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IUser from) {
        setId(from.getId());
        setUsername(from.getUsername());
        setPassword(from.getPassword());
        setLastmodifiedby(from.getLastmodifiedby());
        setLastmodifieddatetime(from.getLastmodifieddatetime());
    }

    @Override
    public <E extends IUser> E into(E into) {
        into.from(this);
        return into;
    }
}