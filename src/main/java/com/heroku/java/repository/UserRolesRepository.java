package com.heroku.java.repository;

import com.heroku.java.model.UserRoles;
import com.heroku.java.model.UserRolesLookup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRolesRepository {

    @Autowired
    SessionFactory sessionFactory;

    public UserRolesLookup findRoleById(int roleId) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserRolesLookup> query = session.createQuery("FROM UserRolesLookup WHERE userRolesLookUpId = :roleId",
                    UserRolesLookup.class);
            query.setParameter("roleId", roleId);
            UserRolesLookup role = query.uniqueResult();
            return role;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserRoles> getAllRoles() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserRoles> query = session.createQuery("FROM UserRoles ", UserRoles.class);
            List<UserRoles> roles = query.getResultList();
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteAllRolesByUserId(int userId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM UserRoles u WHERE u.user.userId = :userId");
            query.setParameter("userId", userId);
            int deleted = query.executeUpdate();
            transaction.commit();
            if (deleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public List<UserRoles> getAllRolesByUser(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserRoles> query = session.createQuery("FROM UserRoles u where u.user.id = :userId", UserRoles.class);
            query.setParameter("userId", userId);
            List<UserRoles> roles = query.getResultList();
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
