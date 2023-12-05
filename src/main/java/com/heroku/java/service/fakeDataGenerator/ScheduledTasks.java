package com.heroku.java.service.fakeDataGenerator;

import com.heroku.java.model.Errors;
import com.heroku.java.model.Machine;
import com.heroku.java.model.Product;
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
    @Scheduled(fixedRate = 6000000) // 10 minutes
    public void generateFakeData() {
        machineService.getAllMachines().forEach(machine -> {
            if (machine.shouldCreateFakeData() &&
                    machine.isMachineRunning() &&
                    machine.getCurrentBatch() != null &&
                    productService.getNumberOfProducedItemsInBatch(machine.getCurrentBatch().getBatchNo()) < machine.getCurrentBatch().getBatchSize())
            {
                productService.saveProduct(generateRandomProduct(machine));

                /*TODO: This is for creating fake machine errors. Maybe move these to another scheduled function, which runs less often.
                Errors error = generateRandomErrorHistory(machine.getMachineID());
                errorService.registerErrorCode(error);*/
            }
        });
    }

    /**
     * Takes a snapshot of every machine which has "enablesnapshot" enabled.
     */
    @Scheduled(fixedRate = 3600000) //1 hour
    public void generateSnapshot() {
        for (Machine machine : machineService.getAllMachinesForSnapshot()) {
            machineUpTimeService.saveMachineUpTime(machine.toMachineUpTime());
        }
    }


    public Product generateRandomProduct(Machine machine) {
        Product product = new Product();
        if (Math.random() <= 0.8) { //Will generate STATUS_OK 80% of the time.
            product.setProductLookupId(1);
        } else {
            product.setProductLookupId(productLookupService.getRandomProductLookUp().getProductLookupId());
        }
        product.setBatchNo(machine.getCurrentBatch());
        //product.setBatchNo(machine.getCurrentBatch().getBatchNo());
        product.setTimeStamp(LocalDateTime.now());
        product.setFake(true);

        return product;
    }

    public Errors generateRandomError(int machineId) {
        Errors error = new Errors();
        error.setErrorLookUpId(errorLookupService.getRandomErrorLookup().getErrorLookupId());
        error.setTimeStamp(LocalDateTime.now());
        error.setMachineID(machineId);
        error.setAsFakeData(true);
        return error;
    }
}
