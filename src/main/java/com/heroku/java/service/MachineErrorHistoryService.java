package com.heroku.java.service;

import com.heroku.java.model.MachineErrorHistory;
import com.heroku.java.repository.MachineErrorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MachineErrorHistoryService {

    @Autowired
    private MachineErrorHistoryRepository machineErrorHistoryRepository;
    public String registerMachineErrorHistory (int machineId, int errorId, LocalDateTime timeForMistake) {
        MachineErrorHistory machineErrorHistory = new MachineErrorHistory(machineId,errorId,timeForMistake);
        return machineErrorHistoryRepository.saveMachineErrorHistory(machineErrorHistory);
    }

    public String registerMachineErrorHistory (MachineErrorHistory machineErrorHistory) {
        return machineErrorHistoryRepository.saveMachineErrorHistory(machineErrorHistory);
    }

    public List<MachineErrorHistory> getMachineErrorHistoryById(Integer historyId) {
        return machineErrorHistoryRepository.getMachineErrorHistoryById(historyId);

    }

    public List<MachineErrorHistory> getAllMEH()
    {
        return machineErrorHistoryRepository.getAllMEH();
    }

}
