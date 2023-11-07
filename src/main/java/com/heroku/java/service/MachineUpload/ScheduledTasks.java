package com.heroku.java.service.MachineUpload;

import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
//    @Autowired
//    private MachineUploadRepository machineRepository;
//    ArrayList<String> machineName = new ArrayList<>();
//    ArrayList<String> status = new ArrayList<>();
//    ArrayList<String> failCode = new ArrayList<>();
//    Random random = new Random();
//    {
//        // Add to machineName
//        machineName.add("Machine1");
//        machineName.add("Machine2");
//        machineName.add("Machine3");
//
//        // Add to status
//        status.add("Online");
//        status.add("Offline");
//        status.add("Maintenance");
//
//        // Add to failCode
//        failCode.add("Error001");
//        failCode.add("Error002");
//        failCode.add("Error003");
//    }
//
//    @Scheduled(fixedRate = 600000)  // 600000 milliseconds = 10 minutes
//    public void insertDataEveryTenMinutes() {
//        String randomMachineName = machineName.get(random.nextInt(machineName.size()));
//        String randomStatus = status.get(random.nextInt(status.size()));
//        String randomFailCode = failCode.get(random.nextInt(failCode.size()));
//
//        Machine machine = new Machine(randomMachineName, randomStatus, randomFailCode, LocalDateTime.now());
//        machineRepository.saveMachine(machine);
//    }
}
