package br.com.aceleradev.javasquad3.errorlogger.service;

import br.com.aceleradev.javasquad3.errorlogger.repository.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    @Autowired
    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    //TODO: ADD METHODS FOR HANDLING ENVIRONMENTS.

}
