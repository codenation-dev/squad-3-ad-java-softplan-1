package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.mapper.ErrorMapper;
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

    public void saveError(ErrorDTO errorDTO){
        errorRepository.save(ErrorMapper.toError(errorDTO));
    }

}
