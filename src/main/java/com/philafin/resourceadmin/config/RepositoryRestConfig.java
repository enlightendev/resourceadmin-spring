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

/**
 * This configures the behavior of spring data rest.
 * http://projects.spring.io/spring-data-rest/
 */
@Configuration
public class RepositoryRestConfig extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
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
