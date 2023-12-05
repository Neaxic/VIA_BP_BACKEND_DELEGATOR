package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.Errors;
import com.heroku.java.model.MachineUpTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ErrorRepository {

    @Autowired
    SessionFactory sessionFactory;

    public String saveErrorCode(Errors errors) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(errors);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public Errors getErrorById(int errorID) {
        try (Session session = sessionFactory.openSession()) {
            Query<Errors> query = session.createQuery("FROM Errors WHERE errorId = :errorID", Errors.class);
            query.setParameter("errorID", errorID);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Errors> getAllErrors() {
        try (Session session = sessionFactory.openSession()) {
            Query<Errors> query = session.createQuery("FROM Errors ", Errors.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Errors getRandomErrorCode() {
        List<Errors> errorCodes = getAllErrors();
        return errorCodes.get((int) (Math.random() * errorCodes.size()));
    }

    public Errors getMostFrequentError(int machineId) { //TODO: MAKE ENDPOINT
        try (Session session = sessionFactory.openSession()) {
            Query<Errors> query = session.createQuery("SELECT e FROM Errors e WHERE e.machineID = :machineId GROUP BY e.errorLookUpId ORDER BY COUNT(e.errorLookUpId) DESC", Errors.class);
            query.setParameter("machineId", machineId);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //TODO: Get 24 hours frequentcy of error
}
