package com.heroku.java.service;

import com.heroku.java.model.ProductLookUp;
import com.heroku.java.repository.ProductLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLookupService {
    @Autowired
    private ProductLookupRepository productLookupRepository;

    public String registerProductLookup(String name, String description){
        ProductLookUp productLookUp = new ProductLookUp();
        productLookUp.setName(name);
        productLookUp.setDescription(description);
        return productLookupRepository.saveProductLookup(productLookUp);
    }

    public ProductLookUp getProductLookupById(Integer productLookupId) {
        return productLookupRepository.getProductLookUpById(productLookupId);
    }

    public List<ProductLookUp> getAllProductLookUps()
    {
        return productLookupRepository.getAllProductLookUp();
    }

    public ProductLookUp getRandomProductLookUp()
    {
        return productLookupRepository.getRandomProductLookUp();
    }
}
