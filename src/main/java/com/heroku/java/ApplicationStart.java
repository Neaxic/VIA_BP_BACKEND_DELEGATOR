package com.heroku.java;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;


@SpringBootApplication
@Controller
@EnableScheduling
@EntityScan("com.heroku.java") // Add this line
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


    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
