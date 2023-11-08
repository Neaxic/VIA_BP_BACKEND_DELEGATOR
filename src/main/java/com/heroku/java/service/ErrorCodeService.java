package com.heroku.java.service;

import com.heroku.java.model.ErrorCode;
import com.heroku.java.repository.ErrorCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorCodeService {

    @Autowired
    private ErrorCodeRepository errorCodeRepository;

    public String registerErrorCode(String errorCode){
        ErrorCode errorCode1 = new ErrorCode(errorCode);
        return errorCodeRepository.saveErrorCode(errorCode1);
    }

    public ErrorCode getErrorCodeById(Integer errorId) {
        return errorCodeRepository.getErrorCodeById(errorId);
    }

    public List<ErrorCode> getAllErrorCodes()
    {
        return errorCodeRepository.getAllErrorCodes();
    }

    public ErrorCode getRandomErrorCode()
    {
        return errorCodeRepository.getRandomErrorCode();
    }

}
