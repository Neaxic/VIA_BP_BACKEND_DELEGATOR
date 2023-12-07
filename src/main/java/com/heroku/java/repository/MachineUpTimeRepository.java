package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.Machine;
import com.heroku.java.model.MachineUpTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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

    public int getMostDowntimeMachine24Hour(){
        try (Session session = sessionFactory.openSession()) {
            LocalDateTime oneDayAgo = LocalDateTime.now().minusHours(24);
            Date dateTwentyFourHoursAgo = Date.from(oneDayAgo.atZone(ZoneId.systemDefault()).toInstant());

            Query<Object[]> query = session.createQuery(
                    "SELECT machineId, COUNT(machineId) as cnt FROM MachineUpTime " +
                            "WHERE status != 1 AND timeOfLog >= :oneDayAgo " +
                            "GROUP BY machineId ORDER BY cnt DESC", Object[].class);
            query.setParameter("oneDayAgo", oneDayAgo);
            query.setMaxResults(1);

            List<Object[]> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                // Assuming that the machineId is an Integer.
                return (Integer) resultList.get(0)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Object[]> getMachineOverviewByMachineLast24(int machineId){
        List<Object[]> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery(" SELECT machinename, status\n" +
                    "FROM machineuptime\n" +
                    "WHERE machineid = :machineId\n" +
                    "ORDER BY timestamp DESC\n" +
                    "LIMIT 24;", Object[].class);
            query.setParameter("machineId", machineId);
            Object result = query.getResultList();
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Object[]> getMachineOverviewAllMachineLast24(){
        List<Object[]> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery("SELECT machinename, status FROM ( SELECT *, ROW_NUMBER() OVER(PARTITION BY machineid ORDER BY timestamp DESC) AS row_num FROM machineuptime ) AS ranked_statuses WHERE row_num <= 24 ORDER BY machineid, timestamp DESC;", Object[].class);
            Object result = query.getResultList();
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}


