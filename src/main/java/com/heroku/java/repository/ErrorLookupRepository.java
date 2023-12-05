package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.ErrorLookup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ErrorLookupRepository {

    @Autowired
    SessionFactory sessionFactory;
    public String saveErrorLookup(ErrorLookup errorLookup) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(errorLookup);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }


    public ErrorLookup getErrorLookupById(int errorLookupId) {
        try (Session session = sessionFactory.openSession()) {
            Query<ErrorLookup> query = session.createQuery("FROM ErrorLookup WHERE errorLookupId = :errorLookupId", ErrorLookup.class);
            query.setParameter("errorLookupId", errorLookupId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<ErrorLookup> getAllErrorLookups() {
        try (Session session = sessionFactory.openSession()) {
            Query<ErrorLookup> query = session.createQuery("FROM ErrorLookup ", ErrorLookup.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public ErrorLookup getRandomErrorLookup() {
        //TODO: This should be done by SQL instead of java for performance.
        List<ErrorLookup> errorLookups = getAllErrorLookups();
        return errorLookups.get((int) (Math.random() * errorLookups.size()));
    }
}
