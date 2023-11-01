package com.heroku.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
@Controller
@EntityScan("com.heroku.java.model") // Add this line
public class ApplicationStart {

    private final DataSource dataSource;
    private final SessionFactory sessionFactory;

    @Autowired
    public ApplicationStart(DataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // You can use sessionFactory for Hibernate operations
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
