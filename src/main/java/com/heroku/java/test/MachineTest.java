package com.heroku.java.test;

import com.heroku.java.model.Machine;
import com.heroku.java.model.MachineDTO;
import com.heroku.java.model.MachineUpTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MachineTest {

    @Test
    public void toDTOTest() {
        int machineId = 1;
        int machineStatus = 2;
        String machineName = "machine_007";
        String machineDescription = "super machine 2000";

        Machine machine = new Machine();
        machine.setMachineID(machineId);
        machine.setStatus(machineStatus);
        machine.setMachineName(machineName);
        machine.setDescription(machineDescription);
        machine.setCreateFakeData(true);

        MachineDTO machineDTO = machine.toDto();
        assertEquals(machineDTO.getMachineID(), machineId);
        assertEquals(machineDTO.getStatus(), machineStatus);
        assertEquals(machineDTO.getMachineName(), machineName);
        assertEquals(machineDTO.getDescription(), machineDescription);
        assertTrue(machineDTO.getCreateFakeData());
    }
    @Test
    public void toMachineUpTimeTest() {
        int machineId = 1;
        int machineStatus = 2;
        String machineName = "Den gule maskine";

        Machine machine = new Machine();
        machine.setMachineID(machineId);
        machine.setStatus(machineStatus);
        machine.setMachineName(machineName);

        MachineUpTime machineUpTime = machine.toMachineUpTime();

        assertNotNull(machineUpTime);
        assertEquals(machineUpTime.getMachineId(), machineId);
        assertEquals(machineUpTime.getStatus(), machineStatus);
        assertEquals(machineUpTime.getMachineName(), machineName);
    }
}
