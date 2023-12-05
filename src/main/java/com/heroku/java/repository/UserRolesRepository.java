package com.heroku.java.repository;

import com.heroku.java.model.User;
import com.heroku.java.model.UserRoles;
import com.heroku.java.model.UserRolesLookup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRolesRepository {

    @Autowired
    SessionFactory sessionFactory;

    public UserRolesLookup findRoleById(int roleId){
        try (Session session = sessionFactory.openSession()) {
            Query<UserRolesLookup> query = session.createQuery("FROM UserRolesLookup WHERE userRolesLookUpId = :roleId", UserRolesLookup.class);
            query.setParameter("roleId", roleId);
            UserRolesLookup role = query.uniqueResult();
            return role;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<UserRoles> getAllRoles(){
        try (Session session = sessionFactory.openSession()){
            Query<UserRoles> query = session.createQuery("FROM UserRoles ", UserRoles.class);
            List<UserRoles> roles = query.getResultList();
            return roles;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
