package com.heroku.java.repository;

import com.heroku.java.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

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


    public List<StatusCodes> getAllStatusCodes() {
        try (Session session = sessionFactory.openSession()) {
            Query<StatusCodes> query = session.createQuery("FROM StatusCodes", StatusCodes.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }




    public StatusCodes getStatusCodeId(int statusCodeID) {
        try (Session session = sessionFactory.openSession()) {
             Query<StatusCodes> query = session.createQuery("FROM StatusCodes WHERE statusCodeID = :param", StatusCodes.class);
            query.setParameter("param", statusCodeID);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
