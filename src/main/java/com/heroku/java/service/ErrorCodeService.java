package com.heroku.java.service;

import com.heroku.java.model.ErrorCode;
import com.heroku.java.model.StatusCodes;
import com.heroku.java.repository.ErrorCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ErrorCodeService {

    @Autowired
    private ErrorCodeRepository errorCodeRepository;

    public String registerErrorCode(String errorCode){
        ErrorCode errorCode1 = new ErrorCode(errorCode);
        return errorCodeRepository.saveErrorCode(errorCode1);
    }

    public ErrorCode getErrorCodeById(Integer errorId) {
        Optional<ErrorCode> errorCode = Optional.ofNullable(errorCodeRepository.getErrorCodeById(errorId));
        return errorCode.orElse(null);
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
