package br.com.aceleradev.javasquad3.errorlogger.service;

import br.com.aceleradev.javasquad3.errorlogger.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    @Autowired
    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    //TODO: ADD METHODS FOR HANDLING ERRORS.

}
