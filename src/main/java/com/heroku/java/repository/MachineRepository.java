package com.heroku.java.repository;

import com.heroku.java.model.BatchInfo;
import com.heroku.java.model.Constants;
import com.heroku.java.model.Machine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MachineRepository  {
    @Autowired
    SessionFactory sessionFactory;
    public String saveMachine(Machine machine) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(machine);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public Machine getMachineById(int machineID) {
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine WHERE machineID = :machineID", Machine.class);
            query.setParameter("machineID", machineID);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}