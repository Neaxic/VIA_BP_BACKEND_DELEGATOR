package com.heroku.java.service;

import com.heroku.java.model.Product;
import com.heroku.java.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(int productId) {
        return productRepository.getProductById(productId);
    }
    public List<Product> getProductsOfBatch(int batchNo) {
        return productRepository.getProductsOfBatch(batchNo);
    }
    public int getNumberOfProducedItemsInBatch(int batchNo) {
        return productRepository.getNumberOfProducedItemsInBatch(batchNo);
    }
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public  Double getCurrentOeeFromBatch(int batchNo) {return productRepository.getCurrentOeeFromBatch(batchNo);}

    public List<Object[]> getMostFrequentStatusForBatch(int batchNo){
        return  productRepository.getMostFrequentStatusForBatch(batchNo);
    }

    public String saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

}
