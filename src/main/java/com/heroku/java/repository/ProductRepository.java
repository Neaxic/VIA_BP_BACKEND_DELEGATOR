package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.Machine;
import com.heroku.java.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product p WHERE p.batchNo.batchNo = :batchNo", Long.class);
            query.setParameter("batchNo", batchNo);
            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
