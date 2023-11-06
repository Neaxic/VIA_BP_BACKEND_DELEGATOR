package com.heroku.java.service;

import com.heroku.java.model.BatchInfo;
import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.User;
import com.heroku.java.repository.BatchInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BatchInfoService {

    @Autowired
    private BatchInfoRepository batchInfoRepository;

    public String registerBatchInfo(Integer batchNo, Integer machineID, Integer producedItems, LocalDateTime startTime, LocalDateTime endTime) {
        BatchInfo batchInfo = new BatchInfo(batchNo, machineID, producedItems,startTime,endTime);
        return batchInfoRepository.saveBatchInfo(batchInfo);
    }
    public BatchInfo getBatchInfoById(Integer id) {
        Optional<BatchInfo> batchInfo = Optional.ofNullable(batchInfoRepository.getBatchInfoById(id));
        return batchInfo.orElse(null);
    }


}
