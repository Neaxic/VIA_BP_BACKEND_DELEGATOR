package com.heroku.java.service;

import com.heroku.java.model.Constants;
import com.heroku.java.model.MachineUpTime;
import com.heroku.java.repository.MachineUpTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.List;

@Service
public class MachineUpTimeService {

    @Autowired
    private MachineUpTimeRepository machineUpTimeRepository;

    public void saveMachineUpTime(MachineUpTime machineUpTime) {
        machineUpTimeRepository.saveMachineUpTime(machineUpTime);
    }

    public double getMachineUpTime24HourProcentage(int machineId) {
        List<MachineUpTime> machineUpTimeList = machineUpTimeRepository.getAllMachineUpTime24Hour(machineId);
        double amountOfSnapshots = machineUpTimeList.size();
        double amountOfRunningSnapshots = 0;
        for (MachineUpTime machineUpTime : machineUpTimeList) {
            if(machineUpTime.getStatus().equals(Constants.MACHINE_STATUS_RUNNING)) {
                amountOfRunningSnapshots++;
            }
        }
        return (amountOfRunningSnapshots/amountOfSnapshots) * 100;
    }

    public int getMostDowntimeMachine24Hour(){
        int machine = machineUpTimeRepository.getMostDowntimeMachine24Hour();
        return machine;
    }

    public int getNumDowntimeLast24Hour(){
        int cnt = machineUpTimeRepository.getNumDowntimeLast24Hour();
        return cnt;
    }

    public int getNumDowntimeLast24HourByMachineId(int machineId){
        int cnt = machineUpTimeRepository.getNumDowntimeLast24HourByMachineId(machineId);
        return cnt;
    }

    public long getTimeSinceLastBreakdown(int machineId){
        long min = machineUpTimeRepository.getTimeSinceLastBreakdown(machineId);
        return min;
    }

    public int getLastErrorCode(int machineId){
        int id = machineUpTimeRepository.getLastNonOperationalStatusCode(machineId);
        return id;
    }

    public String getLastBreakdown(int machineId){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        long min = machineUpTimeRepository.getTimeSinceLastBreakdown(machineId);
        int id = machineUpTimeRepository.getLastNonOperationalStatusCode(machineId);

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                .add("statusCode", id)
                .add("timesince", min);

        jsonArrayBuilder.add(jsonObjectBuilder);
        String json = jsonArrayBuilder.build().toString();
        return json;
    }

}
