package com.heroku.java.service;

import com.heroku.java.model.MachineErrorHistory;
import com.heroku.java.repository.MachineErrorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MachineErrorHistoryService {

    @Autowired
    private MachineErrorHistoryRepository machineErrorHistoryRepository;
    public String registerMachineErrorHistory(int historyId, int machineId, int errorId, LocalDateTime timeForMistake) {
        MachineErrorHistory machineErrorHistory = new MachineErrorHistory(historyId,machineId,errorId,timeForMistake);
        return machineErrorHistoryRepository.saveMachineErrorHistory(machineErrorHistory);
    }


    public MachineErrorHistory getMachineErrorHistoryById(Integer historyId) {
        Optional<MachineErrorHistory> machineErrorHistory = Optional.ofNullable(machineErrorHistoryRepository.getMachineErrorHistoryById(historyId));
        return machineErrorHistory.orElse(null);
    }

}
