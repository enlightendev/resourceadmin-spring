package com.philafin.resourceadmin.config;

import com.philafin.resourceadmin.domain.Application;
import com.philafin.resourceadmin.domain.Employee;
import com.philafin.resourceadmin.domain.Permission;
import com.philafin.resourceadmin.domain.ResourcePermission;
import com.philafin.resourceadmin.repositories.handlers.ApplicationEventHandler;
import com.philafin.resourceadmin.repositories.handlers.EmployeeEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.URI;

/**
 * This configures the behavior of spring data rest.
 * http://projects.spring.io/spring-data-rest/
 */
@Configuration
public class RepositoryRestConfig extends RepositoryRestMvcConfiguration {

    private static final String BASE_URI = "/api";

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.setBaseUri(URI.create(BASE_URI));
        config.exposeIdsFor(Employee.class, Application.class, ResourcePermission.class, Permission.class);
        config.setReturnBodyOnCreate(true);
        config.setReturnBodyOnUpdate(true);
    }

    /** The following section registers beans used for hooking into events from auto generated spring-data-rest endpoints.*/


    /**
     *
     * @return
     */
    @Bean
    EmployeeEventHandler employeeEventHandler() {
        return new EmployeeEventHandler();
    }

    @Bean
    ApplicationEventHandler applicationEventHandler() {
        return new ApplicationEventHandler();
    }

}
