package com.heroku.java.repository;

import com.heroku.java.model.BatchInfo;
import com.heroku.java.model.Constants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BatchInfoRepository {
    @Autowired
    SessionFactory sessionFactory;
    public String saveBatchInfo(BatchInfo batchInfo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(batchInfo);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public BatchInfo getBatchInfoById(int batchNo) {
        try (Session session = sessionFactory.openSession()) {
            Query<BatchInfo> query = session.createQuery("FROM BatchInfo WHERE batchNo = :batchNo", BatchInfo.class);
            query.setParameter("batchNo", batchNo);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<BatchInfo> getAllBatchs() {
        try (Session session = sessionFactory.openSession()) {
            Query<BatchInfo> query = session.createQuery("FROM BatchInfo ", BatchInfo.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
