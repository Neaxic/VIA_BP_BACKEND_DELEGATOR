package com.heroku.java.service;

import com.heroku.java.model.StatusCodes;
import com.heroku.java.repository.StatusCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusCodeService {

    @Autowired
    private StatusCodeRepository statusCodeRepository;
    public String registerStatusCode(String statusName) {
        StatusCodes statusCodes = new StatusCodes(statusName);
        return statusCodeRepository.saveStatusCodes(statusCodes);
    }
    public StatusCodes getStatusCodeById(Integer statusCodeId) {
        Optional<StatusCodes> statusCode = Optional.ofNullable(statusCodeRepository.getStatusCodeId(statusCodeId));
        return statusCode.orElse(null);
    }

    public List<StatusCodes> getAllStatusCodes()
    {
        return statusCodeRepository.getAllStatusCodes();
    }



}
