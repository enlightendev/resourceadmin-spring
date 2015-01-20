package com.philafin.resourceadmin.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 */
@Entity
@Table(name = "resource_permission")
@NamedQuery(name = "ResourcePermission.findAll", query = "SELECT r FROM ResourcePermission r")
public class ResourcePermission extends AbstractPersistable<Long> {

    private String permission;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_type")
    private String resourceType;

    //bi-directional many-to-one association to Employee
    @ManyToOne
    private Employee employee;

    public ResourcePermission() {
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
