package com.heroku.java.controller;

import com.heroku.java.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @Autowired
    private SessionFactory sessionFactory;



    @RequestMapping("/testConnection")
    public String connectedToServer() {
        return "Connected to Server!";
    }

    @RequestMapping("/test")
    public String test() {
        //Session session

       /* try {
            Car car = new Car("Blå", "VW", 280);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(car);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/
        return "Hallo world";
    }

    @RequestMapping("/registerUser")
    public String registerUser() {
      String returnVar = "failed";
        try (Session session = sessionFactory.openSession()) {
            // Create a new User object with the user's details
            User user = new User("userFromApi", "pass", true);

            // Begin a transaction
            session.beginTransaction();

            // Save the user to the database
            session.save(user);

            // Commit the transaction
            session.getTransaction().commit();

            returnVar = "User registered successfully";
        } catch (Exception e) {
            // Handle exceptions, e.g., log the error
            e.printStackTrace();
            returnVar = "Error while registering the user";
        }
      return returnVar;
    }
}
