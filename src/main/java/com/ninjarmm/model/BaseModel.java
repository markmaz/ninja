package com.ninjarmm.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class BaseModel<T extends BaseModel<T>> {
    private OffsetDateTime lastModifiedDateTime;
    private String lastModifiedBy;
    private UUID id;

    @SuppressWarnings("unchecked")
    public T id(UUID id){
        this.id = id;
        return (T) this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    public T getLastModifiedDateTime(OffsetDateTime lastModifiedDateTime){
        this.lastModifiedDateTime = lastModifiedDateTime;
        return (T) this;
    }

    public OffsetDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(OffsetDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    @SuppressWarnings("unchecked")
    public T getLastModifiedBy(String lastModifiedBy){
        this.lastModifiedBy = lastModifiedBy;
        return (T) this;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel<?> baseModel = (BaseModel<?>) o;
        return Objects.equals(lastModifiedDateTime, baseModel.lastModifiedDateTime) &&
                Objects.equals(lastModifiedBy, baseModel.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastModifiedDateTime, lastModifiedBy);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }
}
