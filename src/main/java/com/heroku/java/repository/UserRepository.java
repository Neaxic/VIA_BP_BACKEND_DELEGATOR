package com.heroku.java.repository;

import com.heroku.java.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    public String addRoleLink(UserRoles roleLinker){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(roleLinker);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public String removeRoleLink(UserRoles roleLinker){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.remove(roleLinker);
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
            query.setMaxResults(1);
            User user = query.uniqueResult();
            return user;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<UserRolesLookup> getRolesByUserId(int userID){
        try (Session session = sessionFactory.openSession()){
            Query<UserRolesLookup> query = session.createQuery("FROM UserRoles WHERE user_id = :userId", UserRolesLookup.class);
            query.setParameter("userId", userID);
            List<UserRolesLookup> roles = query.getResultList();
            return roles;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers(){
        try (Session session = sessionFactory.openSession()){
            Query<User> query = session.createQuery("FROM User", User.class);
            List<User> users = query.getResultList();
            return users;
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
            //if (user != null && EncryptionUtil.verifyPassword(user.getPassword(), password)) {
            //    return user; // User is authenticated
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyPassword(String username, String password){
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            if (user != null && EncryptionUtil.verifyPassword(user.getPassword(), password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
