## About

Resource admin application is used to manage the security and access of our enterprise resources. This part of the
application will mainly serve apis to be used by other front end applications, process, or devices.

## Core Technology
- java 1.8
- spring boot

## Development Process

1 - initial creation was done using https://start.spring.io/
because it is a spring boot application and we included security and web libraries spring boot
will implement default basic http security.


## Security

Reference

cors:

- https://spring.io/guides/gs/rest-service-cors/

http security:

- see http://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/reference/htmlsingle/#multiple-httpsecurity

oauth2:

- https://spring.io/blog/2014/11/07/webinar-replay-security-for-microservices-with-spring-and-oauth2

angular-spring:

- http://spring.io/blog/2015/01/12/spring-and-angular-js-a-secure-single-page-application


TODO:

- why doesn't CORS filter get invoked for login page (or any protected resources). CORS filter is only invoked after you have logged in.
