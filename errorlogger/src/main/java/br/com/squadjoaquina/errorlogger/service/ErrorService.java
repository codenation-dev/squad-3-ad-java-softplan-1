package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.mapper.ErrorMapper;
import br.com.squadjoaquina.errorlogger.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    @Autowired
    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    public String saveError(ErrorDTO errorDTO){
        errorDTO.setCreateAt(new Timestamp(System.currentTimeMillis()));
        errorRepository.save(ErrorMapper.toError(errorDTO));
        return "Erro salvo com sucesso!";
    }
}
