package com.heroku.java.model;

import com.heroku.java.util.Constants;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface MachineInterface {
    public int getMachineID();
    public void setMachineID(int machineID);
    public String getMachineName();
    public void setMachineName(String machineName);
    public String getDescription();
    public void setDescription(String description);
    public Integer getStatus();
    public void setStatus(Integer status);
    public Boolean shouldCreateFakeData();
    public void setCreateFakeData(Boolean createFakeData);
    public boolean isMachineRunning();
    public MachineDTO toDto();
}
