package com.heroku.java.repository;

import com.heroku.java.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class StatusCodeRepository {
    @Autowired
    SessionFactory sessionFactory;
    public String saveStatusCodes(StatusCodes statusCodes) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(statusCodes);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public StatusCodes getStatusCodeId(int statusCodeID) {
        try (Session session = sessionFactory.openSession()) {
            Query<StatusCodes> query = session.createQuery("FROM StatusCodes WHERE statusCodeID = :statusCodeID", StatusCodes.class);
            query.setParameter("statusCodeID", statusCodeID);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}