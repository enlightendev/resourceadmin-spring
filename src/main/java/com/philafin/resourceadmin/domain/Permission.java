package com.philafin.resourceadmin.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 */
@Entity
@Table(name = "permissions")
@NamedQuery(name = "Permissions.findAll", query = "SELECT a FROM Permission a")
public class Permission extends AbstractPersistable<Long> {

    private String permission;

    private String description;

    private String tags;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_type")
    private String resourceType;

    public Permission() {
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return String.format("Permissions[id=%d, permission='%s']", getId(), permission);
    }
}
