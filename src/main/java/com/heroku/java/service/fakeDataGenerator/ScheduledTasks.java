package com.heroku.java.service.fakeDataGenerator;

import com.heroku.java.model.Errors;
import com.heroku.java.model.Machine;
import com.heroku.java.model.Product;
import com.heroku.java.model.ProductLookUp;
import com.heroku.java.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasks {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private ErrorLookupService errorLookupService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductLookupService productLookupService;

    @Autowired
    private MachineUpTimeService machineUpTimeService;

    /**
     * Generates fake MachineErrorHistory for each machine at a predefined interval.
     */
    @Scheduled(fixedRate = 900000) //15 minutes https://unitchefs.com/milliseconds/minutes/900000/
    public void generateFakeData() {
        machineService.getAllMachines().forEach(machine -> {
            if (machine.shouldCreateFakeData() &&
                    machine.isMachineRunning() &&
                    machine.getCurrentBatch() != null &&
                    productService.getNumberOfProducedItemsInBatch(machine.getCurrentBatch().getBatchNo()) < machine.getCurrentBatch().getBatchSize())
            {
                productService.saveProduct(generateRandomProduct(machine));

            }
        });
    }

    /**
     * Takes a snapshot of every machine which has "enablesnapshot" enabled.
     */
    @Scheduled(fixedRate = 3600000) //1 hour https://unitchefs.com/milliseconds/minutes/12000/
    public void generateSnapshot() {
        for (Machine machine : machineService.getAllMachinesForSnapshot()) {
            machineUpTimeService.saveMachineUpTime(machine.toMachineUpTime());
        }
    }

    @Scheduled(fixedRate = 1800000) //1 hour https://unitchefs.com/milliseconds/minutes/12000/
    public void generateError() {
        machineService.getAllMachines().forEach(machine -> {
            if (machine.isMachineRunning() && machine.getCurrentBatch() != null) {
                Errors error = generateRandomError(machine.getMachineID());
                errorService.registerErrorCode(error);
            }
        });
    }


    public Product generateRandomProduct(Machine machine) {
        Product product = new Product();
        product.setProductLookup((Math.random() <= 0.8) ? productLookupService.getProductLookupById(1) : productLookupService.getRandomProductLookUp());
        product.setBatchInfo(machine.getCurrentBatch());
        product.setTimeStamp(LocalDateTime.now());
        product.setFake(true);

        return product;
    }

    public Errors generateRandomError(int machineId) {
        Errors error = new Errors();
        error.setErrorLookUp(errorLookupService.getRandomErrorLookup());
        error.setTimeStamp(LocalDateTime.now());
        error.setMachineID(machineId);
        error.setAsFakeData(true);
        return error;
    }
}
