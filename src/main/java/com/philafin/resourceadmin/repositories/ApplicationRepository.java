package com.philafin.resourceadmin.repositories;

import com.philafin.resourceadmin.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 */
@RepositoryRestResource(collectionResourceRel = "applications", path = "applications")
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
