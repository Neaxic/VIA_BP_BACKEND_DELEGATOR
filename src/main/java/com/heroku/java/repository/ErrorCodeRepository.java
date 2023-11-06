package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.ErrorCode;
import com.heroku.java.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorCodeRepository{
    @Autowired
    SessionFactory sessionFactory;

    public String saveErrorCode(ErrorCode errorCode) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(errorCode);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public ErrorCode getErrorCodeById(int errorID) {
        try (Session session = sessionFactory.openSession()) {
            Query<ErrorCode> query = session.createQuery("FROM ErrorCode WHERE errorID = :errorID", ErrorCode.class);
            query.setParameter("errorID", errorID);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
