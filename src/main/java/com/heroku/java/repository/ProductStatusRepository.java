package com.heroku.java.repository;

import com.heroku.java.model.Constants;
import com.heroku.java.model.Product;
import com.heroku.java.model.ProductLookUp;
import com.heroku.java.model.ProductStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductStatusRepository {

    @Autowired
    SessionFactory sessionFactory;
    public String saveProductStatus(ProductStatus ProductStatus) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(ProductStatus);
            session.getTransaction().commit();
            return Constants.STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.STATUS_FAILED;
        }
    }


    public List<ProductStatus> getAllProductsStatus() {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductStatus> query = session.createQuery("FROM ProductStatus", ProductStatus.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

