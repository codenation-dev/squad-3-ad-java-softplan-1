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

    public void delete(Long id) {
        errorRepository.deleteById(id);
    }

}
