package com.philafin.resourceadmin.repositories;

import com.philafin.resourceadmin.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 */
@RepositoryRestResource(collectionResourceRel = "permissions", path = "permissions")
public interface PermissionRepository extends JpaRepository<Permission, Long> {


}
