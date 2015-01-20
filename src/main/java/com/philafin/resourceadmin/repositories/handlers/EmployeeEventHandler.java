package com.philafin.resourceadmin.repositories.handlers;

import com.philafin.resourceadmin.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Handler class for events generated during calls to rest end points.
 */
@RepositoryEventHandler(Employee.class)
public class EmployeeEventHandler {

    private final static Logger log = LoggerFactory.getLogger(EmployeeEventHandler.class);

    @PreAuthorize("hasRole('save-employee')")
    @HandleBeforeSave
    public void handleBeforeSave(Employee employee) {

        log.debug("EmployeeEventHandler::checkSaveAuthority");


    }
}
