package com.philafin.resourceadmin.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee extends AbstractPersistable<Long> {

    private String department;

    private String email;

    private String username;

    private String password;

    private boolean enabled;

    private String fname;

    @Column(name = "is_manager")
    private byte isManager;

    private String lname;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "phone_cell")
    private String phoneCell;

    @Column(name = "phone_home")
    private String phoneHome;

    @Column(name = "phone_office")
    private String phoneOffice;

    private String tags;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "termination_date")
    private Date terminationDate;

    private String title;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //bi-directional many-to-one association to ResourcePermission
    @OneToMany(mappedBy = "employee")
    private List<ResourcePermission> resourcePermissions;

    public Employee() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public byte getIsManager() {
        return this.isManager;
    }

    public void setIsManager(byte isManager) {
        this.isManager = isManager;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getManagerId() {
        return this.managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getPhoneCell() {
        return this.phoneCell;
    }

    public void setPhoneCell(String phoneCell) {
        this.phoneCell = phoneCell;
    }

    public String getPhoneHome() {
        return this.phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneOffice() {
        return this.phoneOffice;
    }

    public void setPhoneOffice(String phoneOffice) {
        this.phoneOffice = phoneOffice;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getTerminationDate() {
        return this.terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ResourcePermission> getResourcePermissions() {
        return this.resourcePermissions;
    }

    public void setResourcePermissions(List<ResourcePermission> resourcePermissions) {
        this.resourcePermissions = resourcePermissions;
    }

    public ResourcePermission addResourcePermission(ResourcePermission resourcePermission) {
        getResourcePermissions().add(resourcePermission);
        resourcePermission.setEmployee(this);
        return resourcePermission;
    }

    public ResourcePermission removeResourcePermission(ResourcePermission resourcePermission) {
        getResourcePermissions().remove(resourcePermission);
        resourcePermission.setEmployee(null);

        return resourcePermission;
    }

    @Override
    public String toString() {

        return String.format("Employee[id=%d, firstName='%s', lastName='%s', created=%tB %te, %tY]", getId(), fname, lname, getCreatedAt(), getCreatedAt(), getCreatedAt(), getCreatedAt());
    }
}
