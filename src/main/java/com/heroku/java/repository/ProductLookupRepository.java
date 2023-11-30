package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.ErrorLookup;
import com.heroku.java.model.ProductLookUp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductLookupRepository {

    @Autowired
    SessionFactory sessionFactory;
    public String saveProductLookup(ProductLookUp ProductLookUp) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(ProductLookUp);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }


    public ProductLookUp getProductLookUpById(int productLookUpId) {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductLookUp> query = session.createQuery("FROM ProductLookUp WHERE productLookupId = :productLookUpId", ProductLookUp.class);
            query.setParameter("productLookUpId", productLookUpId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<ProductLookUp> getAllProductLookUp() {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductLookUp> query = session.createQuery("FROM ProductLookUp", ProductLookUp.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public ProductLookUp getRandomProductLookUp() {
        //TODO: This should be done by SQL instead of java for performance.
        List<ProductLookUp> errorLookups = getAllProductLookUp();
        return errorLookups.get((int) (Math.random() * errorLookups.size()));
    }

}
