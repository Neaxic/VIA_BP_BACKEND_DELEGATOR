package com.heroku.java.service;

import com.heroku.java.model.Machine;
import com.heroku.java.model.StatusCodes;
import com.heroku.java.repository.MachineRepository;
import com.heroku.java.repository.StatusCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;
    public String registerMachine(String machineName, String description, StatusCodes statusCode) {
        Machine machine = new Machine(machineName,description,statusCode);
        return machineRepository.saveMachine(machine);
    }
    public Machine getMachineById(Integer machineId) {
        Optional<Machine> machine = Optional.ofNullable(machineRepository.getMachineById(machineId));
        return machine.orElse(null);
    }

}
