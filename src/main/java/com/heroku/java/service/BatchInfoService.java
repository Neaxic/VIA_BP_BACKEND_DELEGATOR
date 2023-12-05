package com.heroku.java.service;

import com.heroku.java.model.BatchInfo;
import com.heroku.java.repository.BatchInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BatchInfoService {

    @Autowired
    private BatchInfoRepository batchInfoRepository;

    @Autowired
    private MachineService machineService;

    public String registerBatchInfo(Integer machineID, Integer producedItems, LocalDateTime startTime, LocalDateTime endTime) {
        BatchInfo batchInfo = new BatchInfo(machineService.getMachineById(machineID), producedItems,startTime,endTime);
        return batchInfoRepository.saveBatchInfo(batchInfo);
    }
    public BatchInfo getBatchInfoById(Integer id) {
        return batchInfoRepository.getBatchInfoById(id);
    }

    public List<BatchInfo> getAllBatches()
    {
        return batchInfoRepository.getAllBatches();
    }


}
