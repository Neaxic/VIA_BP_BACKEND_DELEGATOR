package com.heroku.java.service;

import com.heroku.java.model.Product;
import com.heroku.java.model.ProductStatus;
import com.heroku.java.repository.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductStatusService {
    @Autowired

    private ProductStatusRepository productStatusRepository;



    public List<ProductStatus> getAllProductsStatus() {
        return productStatusRepository.getAllProductsStatus();
    }
}
