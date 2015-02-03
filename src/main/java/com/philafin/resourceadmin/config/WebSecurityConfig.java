package com.philafin.resourceadmin.config;

import com.philafin.resourceadmin.security.CsrfHeaderFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 *
 * reference: https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-spring-security-part-ii
 */
@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().authenticated();

            http.httpBasic();

            http.csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    //completely ignore urls starting with the following paths
                    .antMatchers("/bower_components/**")
                    .antMatchers("/scripts/**")
                    .antMatchers("/styles/**")
                    .antMatchers("/components/**")
                    .antMatchers("/assets/**")
                    .antMatchers("/app/**")
                    .antMatchers("/fonts/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                .authorizeRequests()
                    .antMatchers("/bak/index.html", "/home.html", "/", "/login", "/api/user").permitAll()
                .anyRequest()
                    .authenticated().and() //without this no one is prompted to authenticate
                .formLogin().permitAll().and()
                .logout().permitAll();
                //.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                //.csrf().csrfTokenRepository(csrfTokenRepository());

            http.csrf().disable();
        }

        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }

    }
}
