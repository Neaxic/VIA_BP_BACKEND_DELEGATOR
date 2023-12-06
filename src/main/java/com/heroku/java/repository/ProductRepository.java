package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
            Query<Product> query = session.createQuery("FROM Product WHERE batchNo = :batchNo", Product.class);
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
}
