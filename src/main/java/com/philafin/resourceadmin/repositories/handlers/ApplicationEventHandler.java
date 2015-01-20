package com.philafin.resourceadmin.repositories.handlers;

import com.philafin.resourceadmin.domain.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 */
@RepositoryEventHandler(Application.class)
public class ApplicationEventHandler {

    private final static Logger log = LoggerFactory.getLogger(ApplicationEventHandler.class);

    @PreAuthorize("hasRole('save-application')")
    @HandleBeforeSave
    public void handleBeforeSave(Application application) {

        log.debug("ApplicationEventHandler::checkSaveAuthority");


    }
}
