/*
 * This file is generated by jOOQ.
 */
package com.ninjarmm.dao.jooq.tables.pojos;


import com.ninjarmm.dao.jooq.tables.interfaces.ISchemaVersion;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "schema_version", schema = "public", uniqueConstraints = {
    @UniqueConstraint(name = "schema_version_pk", columnNames = {"installed_rank"})
}, indexes = {
    @Index(name = "schema_version_s_idx", columnList = "success ASC")
})
public class SchemaVersion implements ISchemaVersion {

    private static final long serialVersionUID = -1651044804;

    private Integer       installedRank;
    private String        version;
    private String        description;
    private String        type;
    private String        script;
    private Integer       checksum;
    private String        installedBy;
    private LocalDateTime installedOn;
    private Integer       executionTime;
    private Boolean       success;

    public SchemaVersion() {}

    public SchemaVersion(ISchemaVersion value) {
        this.installedRank = value.getInstalledRank();
        this.version = value.getVersion();
        this.description = value.getDescription();
        this.type = value.getType();
        this.script = value.getScript();
        this.checksum = value.getChecksum();
        this.installedBy = value.getInstalledBy();
        this.installedOn = value.getInstalledOn();
        this.executionTime = value.getExecutionTime();
        this.success = value.getSuccess();
    }

    public SchemaVersion(
        Integer       installedRank,
        String        version,
        String        description,
        String        type,
        String        script,
        Integer       checksum,
        String        installedBy,
        LocalDateTime installedOn,
        Integer       executionTime,
        Boolean       success
    ) {
        this.installedRank = installedRank;
        this.version = version;
        this.description = description;
        this.type = type;
        this.script = script;
        this.checksum = checksum;
        this.installedBy = installedBy;
        this.installedOn = installedOn;
        this.executionTime = executionTime;
        this.success = success;
    }

    @Id
    @Column(name = "installed_rank", nullable = false, precision = 32)
    @Override
    public Integer getInstalledRank() {
        return this.installedRank;
    }

    @Override
    public SchemaVersion setInstalledRank(Integer installedRank) {
        this.installedRank = installedRank;
        return this;
    }

    @Column(name = "version", length = 50)
    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public SchemaVersion setVersion(String version) {
        this.version = version;
        return this;
    }

    @Column(name = "description", nullable = false, length = 200)
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public SchemaVersion setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "type", nullable = false, length = 20)
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public SchemaVersion setType(String type) {
        this.type = type;
        return this;
    }

    @Column(name = "script", nullable = false, length = 1000)
    @Override
    public String getScript() {
        return this.script;
    }

    @Override
    public SchemaVersion setScript(String script) {
        this.script = script;
        return this;
    }

    @Column(name = "checksum", precision = 32)
    @Override
    public Integer getChecksum() {
        return this.checksum;
    }

    @Override
    public SchemaVersion setChecksum(Integer checksum) {
        this.checksum = checksum;
        return this;
    }

    @Column(name = "installed_by", nullable = false, length = 100)
    @Override
    public String getInstalledBy() {
        return this.installedBy;
    }

    @Override
    public SchemaVersion setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
        return this;
    }

    @Column(name = "installed_on", nullable = false)
    @Override
    public LocalDateTime getInstalledOn() {
        return this.installedOn;
    }

    @Override
    public SchemaVersion setInstalledOn(LocalDateTime installedOn) {
        this.installedOn = installedOn;
        return this;
    }

    @Column(name = "execution_time", nullable = false, precision = 32)
    @Override
    public Integer getExecutionTime() {
        return this.executionTime;
    }

    @Override
    public SchemaVersion setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    @Column(name = "success", nullable = false)
    @Override
    public Boolean getSuccess() {
        return this.success;
    }

    @Override
    public SchemaVersion setSuccess(Boolean success) {
        this.success = success;
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
        final SchemaVersion other = (SchemaVersion) obj;
        if (installedRank == null) {
            if (other.installedRank != null)
                return false;
        }
        else if (!installedRank.equals(other.installedRank))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        }
        else if (!version.equals(other.version))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        }
        else if (!type.equals(other.type))
            return false;
        if (script == null) {
            if (other.script != null)
                return false;
        }
        else if (!script.equals(other.script))
            return false;
        if (checksum == null) {
            if (other.checksum != null)
                return false;
        }
        else if (!checksum.equals(other.checksum))
            return false;
        if (installedBy == null) {
            if (other.installedBy != null)
                return false;
        }
        else if (!installedBy.equals(other.installedBy))
            return false;
        if (installedOn == null) {
            if (other.installedOn != null)
                return false;
        }
        else if (!installedOn.equals(other.installedOn))
            return false;
        if (executionTime == null) {
            if (other.executionTime != null)
                return false;
        }
        else if (!executionTime.equals(other.executionTime))
            return false;
        if (success == null) {
            if (other.success != null)
                return false;
        }
        else if (!success.equals(other.success))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.installedRank == null) ? 0 : this.installedRank.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.script == null) ? 0 : this.script.hashCode());
        result = prime * result + ((this.checksum == null) ? 0 : this.checksum.hashCode());
        result = prime * result + ((this.installedBy == null) ? 0 : this.installedBy.hashCode());
        result = prime * result + ((this.installedOn == null) ? 0 : this.installedOn.hashCode());
        result = prime * result + ((this.executionTime == null) ? 0 : this.executionTime.hashCode());
        result = prime * result + ((this.success == null) ? 0 : this.success.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SchemaVersion (");

        sb.append(installedRank);
        sb.append(", ").append(version);
        sb.append(", ").append(description);
        sb.append(", ").append(type);
        sb.append(", ").append(script);
        sb.append(", ").append(checksum);
        sb.append(", ").append(installedBy);
        sb.append(", ").append(installedOn);
        sb.append(", ").append(executionTime);
        sb.append(", ").append(success);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISchemaVersion from) {
        setInstalledRank(from.getInstalledRank());
        setVersion(from.getVersion());
        setDescription(from.getDescription());
        setType(from.getType());
        setScript(from.getScript());
        setChecksum(from.getChecksum());
        setInstalledBy(from.getInstalledBy());
        setInstalledOn(from.getInstalledOn());
        setExecutionTime(from.getExecutionTime());
        setSuccess(from.getSuccess());
    }

    @Override
    public <E extends ISchemaVersion> E into(E into) {
        into.from(this);
        return into;
    }
}