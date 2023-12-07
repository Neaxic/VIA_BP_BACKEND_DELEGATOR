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
import java.time.temporal.ChronoUnit;
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


    //DE her to mangler kærelighed - de reffeltkere ikke at error er den samme pr snapshot aka pr breakdown
    //De burde være individuelle, hvis nu der er et breakdown, og den skifter til en anden error, så det en ny
    public int getNumDowntimeLast24Hour(){
        try (Session session = sessionFactory.openSession()) {
            LocalDateTime oneDayAgo = LocalDateTime.now().minusHours(24);
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(machineId) as cnt FROM MachineUpTime " +
                            "WHERE status != 1 AND timeOfLog >= :oneDayAgo ", Long.class);


            query.setParameter("oneDayAgo", oneDayAgo);
            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getNumDowntimeLast24HourByMachineId(int machineId){
        try (Session session = sessionFactory.openSession()) {
            LocalDateTime oneDayAgo = LocalDateTime.now().minusHours(24);
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(machineId) as cnt FROM MachineUpTime " +
                            "WHERE status != 1 AND timeOfLog >= :oneDayAgo AND machineId = :machineId ", Long.class);
            query.setParameter("oneDayAgo", oneDayAgo);
            query.setParameter("machineId", machineId);
            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }



    public long getTimeSinceLastBreakdown(int machineId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT timeOfLog FROM MachineUpTime " +
                            "WHERE machineId = :machineId AND status != 1 " +
                            "ORDER BY timeOfLog DESC",
                    Object[].class
            );
            query.setParameter("machineId", machineId);
            query.setMaxResults(1); //Kun den sidste

            Object[] result = query.uniqueResult();

            if (result != null) {
                LocalDateTime lastBreakdownTime = (LocalDateTime) result[0];
                long minutesSinceLastBreakdown = ChronoUnit.MINUTES.between(lastBreakdownTime, LocalDateTime.now());
                return minutesSinceLastBreakdown;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getLastNonOperationalStatusCode(int machineId) {
        Integer lastStatusCode = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(
                    "SELECT status FROM MachineUpTime " +
                            "WHERE machineId = :machineId AND status != 1 ",
                    Integer.class
            );
            query.setParameter("machineId", machineId);
            query.setMaxResults(1);

            lastStatusCode = query.uniqueResult();
            if(lastStatusCode != null){
                return lastStatusCode;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastStatusCode;
    }
}
