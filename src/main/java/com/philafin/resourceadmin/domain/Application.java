package com.philafin.resourceadmin.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Timestamp;


/**
 */
@Entity
@Table(name = "applications")
@NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a")
public class Application extends AbstractPersistable<Long> {

    @Column(name = "app_type")
    private String appType;

    @Column(name = "business_owner")
    private String businessOwner;

    @Column(name = "created_at")
    private Timestamp createdAt;

    private String description;

    @Column(name = "documentation_url")
    private String documentationUrl;

    @Column(name = "home_page")
    private String homePage;

    private String name;

    @Column(name = "technical_owner")
    private String technicalOwner;

    @Column(name = "ticket_url")
    private String ticketUrl;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Application() {
    }

    public String getAppType() {
        return this.appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getBusinessOwner() {
        return this.businessOwner;
    }

    public void setBusinessOwner(String businessOwner) {
        this.businessOwner = businessOwner;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentationUrl() {
        return this.documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnicalOwner() {
        return this.technicalOwner;
    }

    public void setTechnicalOwner(String technicalOwner) {
        this.technicalOwner = technicalOwner;
    }

    public String getTicketUrl() {
        return this.ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("Application[id=%d, name='%s']", getId(), name);
    }
}
