## About

Resource admin application is used to manage the security and access of our enterprise
resources. This part of the application will mainly serve apis to be used by other
front end applications, process, or devices.

#TECHNOLOGY STACK
The following sections discuss the technology stack behind the application and any particular
setup/configuration details.

###SPRING BOOT

Spring boot is used to greatly simplify the application configuration and development process.

### JAVA CONFIG

All java based configuration takes place in the config package. Classes in this package are
annotated with @Configuration so that the container picks these classes up and uses them to
configure the appropriate application behavior.

###APPLICATION PROPERTIES
Spring boot will configure much of the runtime characteristics if it finds an
application.properties file in the classpath.


### SPRING DATA REST

Spring data rest is used to greatly simplify the ability to expose entities as restfull resources.

for our purposes we configured the following:
  1 - return entity ids
  2 - return response body on create and update so we can leverage response handler on client side.

see RepositoryRestConfig

Entities are exposed by 1 - creating a class that extends JpaRepository and passes our entity as a generic and 2 -
by tagging our repository class with @RepositoryRestResource

    @RepositoryRestResource(collectionResourceRel = "permissions", path = "permissions")
    public interface PermissionRepository extends JpaRepository<Permission, Long> {


### DATABASE INIT

spring boot will create schema and load data if we include schema.sql and data.sql in
resources folder and set up the correct database properties in application.properties.

NOTE for oracle: add oracle drivers to local maven

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.1.0 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true


## Development Process

1 - initial creation was done using https://start.spring.io/
because it is a spring boot application and we included security and web libraries spring boot
will implement default basic http security.


### SPRING SECURITY

because this is mainly an api server we use spring oauth to protect endpoints

Process: client applications may be web based, mobile, or command line processes (e.g. curl).

##### DEFAULT CONFIG
- depracted

With Spring boot, if spring security is on the classpath then web applications will be secure
by default with ‘basic’ authentication on all HTTP endpoints.  Spring security is applied by
adding 'spring-boot-starter-security' as a dependency.

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

this default configuration provides an in memory username/password and some other items- see docs.

##### OVERRIDING DEFAULTS

We override default configuration by extending WebSecurityConfigurerAdapter and implementing the
appropriate methods in addition to annotating our config class with the following annotations.

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvcSecurity

see WebSecurityConfig

##### USING OUR OWN USER DATA STORE

Also configured in WebSecurityConfig by overriding

    @Override
       public void configure(AuthenticationManagerBuilder auth) throws Exception {

We define the queries and tables containing authentication/authorization info by extending JdbcDaoImpl.
see ApplicationUserDetailsManager

##### PASSWORD ENCODING

-- see how angular-rest-springsecurity does it.

##### PROTECTING END POINTS - SECURE API - WITH AUTHORIZATION
This is accomplished by adding the following annotation: @EnableGlobalMethodSecurity(prePostEnabled = true)
to our WebSecurityConfig class. Then we can annotate each method we wish to protect as follows.

    @PreAuthorize("hasRole('search-employee')")
    @Query("select c from Employee c where  c.lname LIKE :lname")
    List<Application> searchByLastName(@Param("lname") String lname);


http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-security
http://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/reference/htmlsingle/

- securing spring data rest generated endpoints using events
http://jaxenter.com/rest-api-spring-java-8-112289.html
http://docs.spring.io/spring-data/rest/docs/2.2.0.RELEASE/reference/html/#events-chapter
https://github.com/spring-projects/spring-data-rest/wiki/Handling-ApplicationEvents-in-the-REST-Exporter



#### Reference

CORS:
deprecated - not a concern now that we use oauth.

- https://spring.io/guides/gs/rest-service-cors/

http security:
- see http://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/reference/htmlsingle/#multiple-httpsecurity

oauth2:

- https://spring.io/blog/2014/11/07/webinar-replay-security-for-microservices-with-spring-and-oauth2
- https://github.com/dsyer/sparklr-boot

angular-spring:

- http://spring.io/blog/2015/01/12/spring-and-angular-js-a-secure-single-page-application


TODO:

- why doesn't CORS filter get invoked for login page (or any protected resources).
  CORS filter is only invoked after you have logged in. (FIXED by applying the following:

  `
      @Component
      @Order(Ordered.HIGHEST_PRECEDENCE)
  `


















### ANGULAR


#### security

    http://www.sitepoint.com/implementing-authentication-angular-applications/
    https://medium.com/opinionated-angularjs/techniques-for-authentication-in-angularjs-applications-7bbf0346acec
    http://jaxenter.com/rest-api-spring-java-8-112289.html
    http://devcenter.kinvey.com/angular/tutorials/how-to-implement-safe-signin-via-oauth
    http://blog.techdev.de/using-spring-oauth-in-trackr/

securing routes:

    http://www.sitepoint.com/implementing-authentication-angular-applications/
    https://medium.com/opinionated-angularjs/techniques-for-authentication-in-angularjs-applications-7bbf0346acec





## ACTUATOR & METRICS

Spring Boot Actuator includes a number of additional features to help you monitor and manage your application when it’s pushed to production.

actuator added end points
/env/{name:.*}
/autoconfig
/dump
/trace
/metrics/{name:.*}
/health
/configprops
/beans
/mappings
/info

# ABOUT CURL
curl is a command line utility that allows you to make http requests to rest endpoints (among other things). Here are
notes on using curl for CRUD operations.

#### oAuth

curl -X POST -vu clientapp:123456 http://localhost:9191/oauth/token -H "Accept: application/json" -d "password=password&username=user&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"



#### Create

employees - embedded post data

`curl -i -X POST -H "Content-Type:application/json" -d "{\"applicationName\":\"Test Curl App\"}" http://localhost:8080/applications`

resource permissions - reference a file with post data.

`curl -i -X POST -H "Content-Type:application/json" -d "@resourcePermission.json" http://localhost:8080/resourcePermissions`

-X POST: signals this a POST used to create a new entry.

-H "Content-Type:application/json" sets the content type so the application knows the payload contains a JSON object

-d "{\"applicationName\":\"Test Curl App\"}" is the data being sent

#### Read

list all employees:

w/o oauth -> `curl http://localhost:8080/employees`
w oauth ---> `curl -H "Authorization: Bearer [token from oauth token request]" http://localhost:8080/employees`

#### Update

PUT replaces an entire record. Fields not supplied will be replaced with null.

`curl -i -X PUT -H "Content-Type:application/json" -d "{\"applicationName\":\"Test Curl App2\"}" http://localhost:8080/applications/1103`

PATCH can be used to update a subset of items.

`curl -X PATCH -H "Content-Type:application/json" -d "{\"applicationName\":\"Test Curl App2\"}" http://localhost:8080/applications/1103`

### Delete

`curl -X DELETE http://localhost:8080/applications/1103`

NOTE - You can specify a file for your payload data to post, put, and patch commands

`curl -i -X POST -H "Content-Type:application/json" -d "@employee.json" http://localhost:8080/employees`

see resources folder in test for data


## REFERENCE
--------------------------------------------------------------------------------------------------------------

angular

                : forms -> http://www.infoq.com/news/2014/09/angular-13-html-forms

                : https://github.com/angular/angular-hint

                : http://blog.thoughtram.io/angularjs/2014/11/06/exploring-angular-1.3-angular-hint.html

--------------------------------------------------------------------------------------------------------------

spring boot

                : http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/

                : http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config

--------------------------------------------------------------------------------------------------------------

spring-data-rest:

                : http://docs.spring.io/spring-data/rest/docs/2.2.0.RELEASE/reference/html/

                : http://spring.io/guides/gs/accessing-data-rest/

                : http://spring.io/understanding/HATEOAS

                : http://docs.spring.io/spring-data/rest/docs/2.2.0.RELEASE/api/org/springframework/data/rest/core/config/RepositoryRestConfiguration.html

                : https://github.com/spring-projects/spring-data-book

                : $resource adapter for spring data rest - > http://guylabs.ch/project/angular-spring-data-rest/

--------------------------------------------------------------------------------------------------------------

profiles

                : https://spring.io/blog/2011/02/14/spring-3-1-m1-introducing-profile/

--------------------------------------------------------------------------------------------------------------

CSRF

                : http://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/

--------------------------------------------------------------------------------------------------------------

metrics & actuator

                : https://github.com/spring-projects/spring-boot/tree/master/spring-boot-actuator

                : http://docs.spring.io/spring-boot/docs/1.0.0.RC5/reference/htmlsingle/#production-ready-code-hale-metrics

                : https://github.com/ryantenney/metrics-spring

                : https://dropwizard.github.io/metrics/3.1.0/

                : https://github.com/jhipster/jhipster-sample-app-2.0


--------------------------------------------------------------------------------------------------------------

apps

                : https://github.com/kms-technology/springboot-angularjs-demo

                : https://bitbucket.org/infinit-group/cvdb

                : restangular example -> http://plnkr.co/edit/d6yDka?p=preview


##TODO
- setup cascade delete, if I delete user delete all application access.
- create retrofit tests
- create RestTemplate tests
- use nggrid or other grid to
  - allow group by (title, dept, manager).
  - sorting, filtering
-

- authentication

- applications should have their list of permissions.
    - user selects resource name, then type and is presented with list of permissions.
    - table name - permission_list (resource_name, resource_type, permission, description)

- add to resource_permission: requested_by, added_by

- activity feed
    - api requests: request for permission,
    - user activity: added, deleted, updated
    - application activity: added


- protect spring-data-rest generated methods ??

