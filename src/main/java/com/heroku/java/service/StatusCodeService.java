package com.heroku.java.service;

import com.heroku.java.model.StatusCodes;
import com.heroku.java.repository.StatusCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusCodeService {

    @Autowired
    private StatusCodeRepository statusCodeRepository;
    public String registerStatusCode(String statusName) {
        StatusCodes statusCodes = new StatusCodes(statusName);
        return statusCodeRepository.saveStatusCodes(statusCodes);
    }
    public StatusCodes getStatusCodeById(Integer statusCodeId) {
        return statusCodeRepository.getStatusCodeId(statusCodeId);
    }

    public List<StatusCodes> getAllStatusCodes()
    {
        return statusCodeRepository.getAllStatusCodes();
    }



}
