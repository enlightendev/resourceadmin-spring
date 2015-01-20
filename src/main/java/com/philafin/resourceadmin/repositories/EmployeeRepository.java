package com.philafin.resourceadmin.repositories;

import com.philafin.resourceadmin.domain.Application;
import com.philafin.resourceadmin.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 */
@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * curl -u JL1234:PWD1234 http://localhost:8080/employees/search/searchByLastName?lname=Lamadrid
     * @param lname
     * @return
     */
    @PreAuthorize("hasRole('search-employee')")
    @Query("select c from Employee c where  c.lname LIKE :lname")
    List<Application> searchByLastName(@Param("lname") String lname);

    //@PreAuthorize("hasRole('search-employee')")
    @Query("select c from Employee c where  c.isManager = 1")
    List<Application> searchForManagers();

}
