/*
 * This file is generated by jOOQ.
 */
package com.ninjarmm.dao.jooq.tables.records;


import com.ninjarmm.dao.jooq.tables.User;
import com.ninjarmm.dao.jooq.tables.interfaces.IUser;

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
@Table(name = "user", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "user_pkey", columnNames = {"id"})
})
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record5<UUID, String, String, String, OffsetDateTime>, IUser {

    private static final long serialVersionUID = 989889799;

    /**
     * Setter for <code>public.user.id</code>.
     */
    @Override
    public UserRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.user.id</code>.
     */
    @Id
    @Column(name = "id", nullable = false)
    @Override
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.user.username</code>.
     */
    @Override
    public UserRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.user.username</code>.
     */
    @Column(name = "username", nullable = false)
    @Override
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.user.password</code>.
     */
    @Override
    public UserRecord setPassword(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.user.password</code>.
     */
    @Column(name = "password", nullable = false)
    @Override
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.user.lastmodifiedby</code>.
     */
    @Override
    public UserRecord setLastmodifiedby(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.user.lastmodifiedby</code>.
     */
    @Column(name = "lastmodifiedby")
    @Override
    public String getLastmodifiedby() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.user.lastmodifieddatetime</code>.
     */
    @Override
    public UserRecord setLastmodifieddatetime(OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.user.lastmodifieddatetime</code>.
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
    public Row5<UUID, String, String, String, OffsetDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<UUID, String, String, String, OffsetDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return User.USER.ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return User.USER.PASSWORD;
    }

    @Override
    public Field<String> field4() {
        return User.USER.LASTMODIFIEDBY;
    }

    @Override
    public Field<OffsetDateTime> field5() {
        return User.USER.LASTMODIFIEDDATETIME;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getPassword();
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
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getPassword();
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
    public UserRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserRecord value4(String value) {
        setLastmodifiedby(value);
        return this;
    }

    @Override
    public UserRecord value5(OffsetDateTime value) {
        setLastmodifieddatetime(value);
        return this;
    }

    @Override
    public UserRecord values(UUID value1, String value2, String value3, String value4, OffsetDateTime value5) {
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

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(UUID id, String username, String password, String lastmodifiedby, OffsetDateTime lastmodifieddatetime) {
        super(User.USER);

        set(0, id);
        set(1, username);
        set(2, password);
        set(3, lastmodifiedby);
        set(4, lastmodifieddatetime);
    }
}
