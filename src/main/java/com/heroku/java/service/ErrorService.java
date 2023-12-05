package com.heroku.java.service;

import com.heroku.java.model.Errors;
import com.heroku.java.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorCodeRepository;

    public String registerErrorCode(Errors errors){
        return errorCodeRepository.saveErrorCode(errors);
    }

    public Errors getErrorCodeById(Integer errorId) {
        return errorCodeRepository.getErrorById(errorId);
    }

    public List<Errors> getAllErrorCodes()
    {
        return errorCodeRepository.getAllErrors();
    }

    public Errors getRandomErrorCode()
    {
        return errorCodeRepository.getRandomErrorCode();
    }

}
