package com.heroku.java.model;


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
