package com.philafin.resourceadmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.philafin.resourceadmin")
@EnableJpaRepositories("com.philafin.resourceadmin.repositories")
@EntityScan(basePackages = {"com.philafin.resourceadmin.domain"})
//@SpringBootApplication
public class ResourceadminApplication implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(ResourceadminApplication.class);

    public static void main(String[] args) {
        log.debug("Entering main!");
        ConfigurableApplicationContext ctx = SpringApplication.run(ResourceadminApplication.class);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}