package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.MachineErrorHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class MachineErrorHistoryRepository {
    @Autowired
    SessionFactory sessionFactory;
    public String saveMachineErrorHistory(MachineErrorHistory machineErrorHistory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(machineErrorHistory);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public MachineErrorHistory getMachineErrorHistoryById(int machineId) {
        try (Session session = sessionFactory.openSession()) {
            Query<MachineErrorHistory> query = session.createQuery("FROM MachineErrorHistory WHERE machineId = :machineId", MachineErrorHistory.class);
            query.setParameter("machineId", machineId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MachineErrorHistory> getAllMEH() {
        try (Session session = sessionFactory.openSession()) {
            Query<MachineErrorHistory> query = session.createQuery("FROM MachineErrorHistory ", MachineErrorHistory.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
