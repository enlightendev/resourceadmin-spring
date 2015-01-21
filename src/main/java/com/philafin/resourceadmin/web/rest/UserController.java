package com.philafin.resourceadmin.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 *
 * reference: https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-spring-security-part-ii
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
