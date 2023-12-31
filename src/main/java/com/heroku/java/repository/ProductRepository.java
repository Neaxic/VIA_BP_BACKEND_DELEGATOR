package com.heroku.java.repository;

import com.heroku.java.util.Constants;
import com.heroku.java.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    SessionFactory sessionFactory;
    public String saveProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }

    public Product getProductById(int productId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE productId = :productId", Product.class);
            query.setParameter("productId", productId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getProductsOfBatch(int batchNo) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE batchInfo.batchNo = :batchNo", Product.class);
            query.setParameter("batchNo", batchNo);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNumberOfProducedItemsInBatch(int batchNo) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product p WHERE p.batchInfo.batchNo = :batchNo", Long.class);
            query.setParameter("batchNo", batchNo);
            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }





    public Double getCurrentOeeFromBatch(int batchNo){
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createNativeQuery("SELECT (COUNT(case when productLookupId = 1 then 1 else null end) * 100.0) / COUNT(*) FROM Product WHERE batchNo = :batchNo", Double.class);
            query.setParameter("batchNo", batchNo);
            Double result = query.getSingleResult();
            return result != null ? result : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public List<Object[]> getMostFrequentStatusForBatch(int batchNo){
        List<Object[]> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery("SELECT p.productLookupId, COUNT(p) as statusCount FROM Product p WHERE p.batchNo = :batchNo AND p.productLookupId != 1 GROUP BY p.productLookupId ORDER BY statusCount DESC", Object[].class);
            query.setParameter("batchNo", batchNo);
            Object result = query.getResultList();
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Object[]> getHistoryBatchData(int machineId) {
        List<Object[]> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery(
                    "SELECT bi.BatchNo, " +
                            "       (SELECT (COUNT(case when p.productLookupId = 1 then 1 else null end) * 100.0) / COUNT(*) FROM Product p WHERE p.batchNo = bi.BatchNo) AS OEE, " +
                            "       (SELECT pl.productLookupId FROM Product pl WHERE pl.batchNo = bi.BatchNo AND pl.productLookupId != 1 GROUP BY pl.productLookupId ORDER BY COUNT(pl) DESC LIMIT 1) AS MostFrequentError, " +
                            "       bi.EndTime " +
                            "FROM BatchInfo bi " +
                            "WHERE bi.MachineID = :machineId", Object[].class);
            query.setParameter("machineId", machineId);
            results = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Object[]> getMostFrequentStatusForMachine(int machineId){
        List<Object[]> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery("SELECT MachineID, productlookupid, COUNT(*) AS Count FROM BatchInfo JOIN Product ON BatchInfo.BatchNo = Product.BatchNo WHERE Product.productlookupid != 1 AND MachineID = :machineId GROUP BY MachineID, productlookupid ORDER BY productlookupid;", Object[].class);
            query.setParameter("machineId", machineId);
            Object result = query.getResultList();
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product ", Product.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }



    public List<Object[]> getMostCommonProductErrorsAndTheirFrequencyLast24hr() {
        try (Session session = sessionFactory.openSession()) {

            Query<Object[]> query = session.createQuery("SELECT p.productLookup.name, COUNT(p.productLookup.name) AS frequency " +
                    "FROM Product p " +
                    "WHERE p.timeStamp >= :oneDayAgo " +
                    "AND p.productLookup.productLookupId <> 1" +
                    "GROUP BY p.productLookup.name " +
                    "ORDER BY frequency DESC", Object[].class);
            query.setParameter("oneDayAgo", LocalDateTime.now().minusDays(1));
            query.setMaxResults(5);

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Object[]> getMostCommonProductErrorsAndTheirFrequency() {
        try (Session session = sessionFactory.openSession()) {

            Query<Object[]> query = session.createQuery("SELECT p.productLookup.name, COUNT(p.productLookup.name) AS frequency " +
                    "FROM Product p " +
                    "WHERE p.productLookup.productLookupId <> 1" +
                    "GROUP BY p.productLookup.name " +
                    "ORDER BY frequency DESC", Object[].class);
            query.setMaxResults(5);

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Object[]> getMostCommonProductErrorsAndTheirFrequencyForMachine(int machineId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createQuery("SELECT p.productLookup.name, COUNT(p.productLookup.name) AS frequency " +
                    "FROM Product p " +
                    "WHERE p.productLookup.productLookupId <> 1 and p.batchInfo.machine.id = :machineId " +
                    "GROUP BY p.productLookup.name " +
                    "ORDER BY frequency DESC", Object[].class);
            query.setParameter("machineId", machineId);
            query.setMaxResults(5);

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Object[]> getMostCommonProductErrorsAndTheirFrequencyForMachine24Hour(int machineId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createQuery("SELECT p.productLookup.name, COUNT(p.productLookup.name) AS frequency " +
                    "FROM Product p " +
                    "WHERE p.timeStamp >= :oneDayAgo " +
                    "AND p.productLookup.productLookupId <> 1 and p.batchInfo.machine.id = :machineId " +
                    "GROUP BY p.productLookup.name " +
                    "ORDER BY frequency DESC", Object[].class);
            query.setParameter("oneDayAgo", LocalDateTime.now().minusDays(1));
            query.setParameter("machineId", machineId);
            query.setMaxResults(5);

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Integer getNumberOfProductsMadeInTheLast24Hours() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(p.id) FROM Product p WHERE p.timeStamp >= :oneDayAgo", Long.class);
            query.setParameter("oneDayAgo", LocalDateTime.now().minusDays(1));
            Long result = query.getSingleResult();
            return result.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getNumberOfProductsMadeInTheLast24HoursPrHour() {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery(
                    "SELECT DATE(p.TimeStamp) as Date, " +
                            "EXTRACT(HOUR FROM p.TimeStamp) as Hour, " +
                            "COUNT(p.ProductId) as ProductCount " +
                            "FROM Product p " +
                            "WHERE p.TimeStamp >= :yesterday " +
                            "GROUP BY Date, EXTRACT(HOUR FROM p.TimeStamp) " +
                            "ORDER BY Date DESC, EXTRACT(HOUR FROM p.TimeStamp) DESC");

            query.setParameter("yesterday", LocalDateTime.now().minusHours(24));
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getNumberOfProductsMadeInTheLast24HoursPrHourPrMachine(int machineId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery(
                    "SELECT DATE(p.TimeStamp) as Date, " +
                            "EXTRACT(HOUR FROM p.TimeStamp) as Hour, " +
                            "COUNT(p.ProductId) as ProductCount " +
                            "FROM Product p " +
                            "JOIN Bachinfo b ON p.batchno = b.batchno "+
                            "JOIN Machine m ON b.machineid = m.machineid "+
                            "WHERE p.TimeStamp >= :yesterday AND WHERE mac  " +
                            "GROUP BY Date, EXTRACT(HOUR FROM p.TimeStamp) " +
                            "ORDER BY Date DESC, EXTRACT(HOUR FROM p.TimeStamp) DESC");

            query.setParameter("yesterday", LocalDateTime.now().minusHours(24));
            query.setParameter("machineId", machineId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getProductsMadeEachDay30DayInterval() {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createNativeQuery(
                    "SELECT DATE(p.TimeStamp) as Date, COUNT(p.ProductId) as ProductCount " +
                            "FROM Product p " +
                            "WHERE p.TimeStamp >= :thirtyDaysAgo " +
                            "GROUP BY Date " +
                            "ORDER BY Date DESC");

            query.setParameter("thirtyDaysAgo", LocalDateTime.now().minusDays(30));
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
