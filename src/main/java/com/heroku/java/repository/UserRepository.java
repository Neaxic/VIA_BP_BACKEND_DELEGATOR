package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    public String saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public User findUserByUsername(String username){
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            return user;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User login(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            if (user != null && EncryptionUtil.verifyPassword(user.getPassword(), password)) {
                return user; // User is authenticated
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
