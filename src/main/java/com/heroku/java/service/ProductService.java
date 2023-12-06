package com.heroku.java.service;

import com.heroku.java.model.Product;
import com.heroku.java.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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

    public String getMostFrequentStatusForBatch(int batchNo){
        Object[] result = productRepository.getMostFrequentStatusForBatch(batchNo).get(0);
        Integer errorLookUpId = (Integer) result[0];
        Integer count = ((Number) result[1]).intValue();

        String json = Json.createObjectBuilder()
                .add("errorLookUpId", errorLookUpId)
                .add("count", count)
                .build()
                .toString();
        return json;
    }
    public String getMostFrequentStatusForMachine(int machineId){
        List<Object[]> results = productRepository.getMostFrequentStatusForMachine(machineId);
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Object[] result : results) {
            Integer machineid = (Integer) result[0];
            Integer productLookUpId = (Integer) result[1];
            Integer count = ((Number) result[2]).intValue();

            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add("machineid", machineid)
                    .add("productlookupid", productLookUpId)
                    .add("count", count);

            jsonArrayBuilder.add(jsonObjectBuilder);
        }

        String json = jsonArrayBuilder.build().toString();
        return json;
    }

    public String saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

}
