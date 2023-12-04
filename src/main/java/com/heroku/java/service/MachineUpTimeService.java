package com.heroku.java.service;

import com.heroku.java.model.MachineUpTime;
import com.heroku.java.repository.MachineUpTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineUpTimeService {

    @Autowired
    private MachineUpTimeRepository machineUpTimeRepository;

    public void saveMachineUpTime(MachineUpTime machineUpTime) {
        machineUpTimeRepository.saveMachineUpTime(machineUpTime);
    }
}
