package com.heroku.java.service;

import com.heroku.java.model.ErrorLookup;
import com.heroku.java.repository.ErrorLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorLookupService {

    @Autowired
    private ErrorLookupRepository errorLookupRepository;

    public String registerErrorLookup(String name, String description){
        ErrorLookup errorLookup = new ErrorLookup();
        errorLookup.setName(name);
        errorLookup.setDescription(description);
        return errorLookupRepository.saveErrorLookup(errorLookup);
    }

    public ErrorLookup getErrorLookupById(Integer errorLookupId) {
        return errorLookupRepository.getErrorLookupById(errorLookupId);
    }

    public List<ErrorLookup> getAllErrorLookups()
    {
        return errorLookupRepository.getAllErrorLookups();
    }

    public ErrorLookup getRandomErrorLookup()
    {
        return errorLookupRepository.getRandomErrorLookup();
    }
}
