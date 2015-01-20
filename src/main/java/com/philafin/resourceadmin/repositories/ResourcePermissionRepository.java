package com.philafin.resourceadmin.repositories;

import com.philafin.resourceadmin.domain.ResourcePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 */
@RepositoryRestResource(collectionResourceRel = "resourcePermissions", path = "resourcePermissions")
public interface ResourcePermissionRepository extends JpaRepository<ResourcePermission, Long> {

    @Query("select c from ResourcePermission c where  c.employee.id = :employeeId")
    List<ResourcePermission> searchByEmployeeId(@Param("employeeId") Long employeeId);

}
