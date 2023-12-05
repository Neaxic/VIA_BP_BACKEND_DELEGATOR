package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.MachineUpTime;
import com.heroku.java.model.ProductLookUp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    public List<MachineUpTime> getAllMachineUpTime24Hour(int machineId) {
        try (Session session = sessionFactory.openSession()) {
            LocalDateTime oneDayAgo = LocalDateTime.now().minusHours(24);
            Date dateTwentyFourHoursAgo = Date.from(oneDayAgo.atZone(ZoneId.systemDefault()).toInstant());
            Query<MachineUpTime> query = session.createQuery("FROM MachineUpTime WHERE machineId = :machineId AND timeOfLog >= :oneDayAgo", MachineUpTime.class);
            query.setParameter("machineId", machineId);
            query.setParameter("oneDayAgo", oneDayAgo);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
