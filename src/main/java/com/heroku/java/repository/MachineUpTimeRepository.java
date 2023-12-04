package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.Machine;
import com.heroku.java.model.MachineUpTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class MachineUpTimeRepository {
    private final SessionFactory sessionFactory;
    @Autowired
    public MachineUpTimeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String saveMachineUpTime(MachineUpTime machineUpTime) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(machineUpTime);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }
}
