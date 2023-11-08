package com.heroku.java.service.fakeDataGenerator;

import com.heroku.java.model.BatchInfo;
import com.heroku.java.model.MachineErrorHistory;
import com.heroku.java.service.ErrorCodeService;
import com.heroku.java.service.MachineErrorHistoryService;
import com.heroku.java.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasks {

    @Autowired
    private MachineErrorHistoryService machineErrorHistoryService;

    @Autowired
    private ErrorCodeService errorCodeService;

    @Autowired
    private MachineService machineService;

    /**
     * Generates fake MachineErrorHistory for each machine at a predefined interval.
     */
    @Scheduled(fixedRate = 600000)  // 10 minutes
    public void generateFakeData() {
        machineService.getAllMachines().forEach(machine -> {
            if (machine.shouldCreateFakeData() && machine.isMachineRunning() && machine.getCurrentBatch() != null) {
                MachineErrorHistory error = generateRandomMachineErrorHistory(machine.getMachineID());
                machineErrorHistoryService.registerMachineErrorHistory(error);
            }
        });
    }


    public MachineErrorHistory generateRandomMachineErrorHistory(int machineId) {
        int errorId = errorCodeService.getRandomErrorCode().getErrorID();
        LocalDateTime timeForMistake = LocalDateTime.now();
        MachineErrorHistory machineErrorHistory = new MachineErrorHistory(machineId, errorId, timeForMistake);
        return machineErrorHistory;
    }
}
