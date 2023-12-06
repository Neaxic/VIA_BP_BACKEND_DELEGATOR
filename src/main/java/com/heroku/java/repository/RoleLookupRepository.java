package com.heroku.java.repository;

import com.heroku.java.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RoleLookupRepository {

    @Autowired
    SessionFactory sessionFactory;

    public List<UserRolesLookup> getAllLookupRoles() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserRolesLookup> query = session.createQuery("FROM UserRolesLookup", UserRolesLookup.class);
            List<UserRolesLookup> roles = query.getResultList();
            return roles;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
