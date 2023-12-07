package com.heroku.java.service;

import com.heroku.java.model.Product;
import com.heroku.java.repository.ProductRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.*;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public String getHistoryBatchData(int machineId){
        List<Object[]> res = productRepository.getHistoryBatchData(machineId);
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Object[] ress : res){
            Integer batchNo = (Integer) ress[0];
            Double oee = ((BigDecimal) ress[1]).doubleValue();
            Integer mostFreqent = (Integer) ress[2];
            Timestamp endtime = (Timestamp) ress[3];

            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add("batchNo", batchNo)
                    .add("oee", oee)
                    .add("mostFreqent", mostFreqent)
                    .add("endtime", dateFormat.format(endtime));  // Format Timestamp her

            jsonArrayBuilder.add(jsonObjectBuilder);
        }

        String json = jsonArrayBuilder.build().toString();
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

    public String getMostCommonErrorsAndTheirFrequency() {
        List<Object[]> object = productRepository.getMostCommonErrorsAndTheirFrequency();
        JsonArrayBuilder jsonReturnArray = Json.createArrayBuilder();
        for (Object[] result : object) {
            JsonObjectBuilder jsonObject = Json.createObjectBuilder()
                    .add("productErrorName", (String) result[0])
                    .add("frequency", (Long) result[1]);
            jsonReturnArray.add(jsonObject);
        }
        return jsonReturnArray.build().toString();
    }

}
