package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.repository.ErrorRepository;
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

    public void saveError(Error error){
        errorRepository.save(error);
    }

}
