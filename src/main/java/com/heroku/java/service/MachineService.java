package com.heroku.java.service;

import com.heroku.java.model.Machine;
import com.heroku.java.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public String registerMachine(String machineName, String description, int statusCode) {
        Machine machine = new Machine(machineName,description,statusCode,false);
        return machineRepository.saveMachine(machine);
    }
    public Machine getMachineById(Integer machineId) {
        return machineRepository.getMachineById(machineId);
    }

    public List<Machine> getAllMachines()
    {
        return machineRepository.getAllMachines();
    }

    public List<Machine> getAllMachinesForSnapshot() { return machineRepository.getAllMachinesForSnapshot(); }

}
