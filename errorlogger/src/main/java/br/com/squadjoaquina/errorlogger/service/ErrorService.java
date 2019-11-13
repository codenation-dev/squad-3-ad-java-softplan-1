package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.controller.paramenum.Criteria;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.dto.ErrorResumeDTO;
import br.com.squadjoaquina.errorlogger.mapper.ErrorMapper;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Error;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.repository.ErrorRepository;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    @Autowired
    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    public ErrorDTO getById(Long id) {
        Optional<Error> opt = errorRepository.findById(id);
        if (opt.isPresent()) {
            return ErrorMapper.toDTO(opt.get());
        } else {
            throw new ErrorNotFoundException();
        }
    }

    public String save(ErrorDTO errorDTO) {
        errorDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        errorRepository.save(ErrorMapper.toError(errorDTO));
        return "Erro salvo com sucesso!";
    }

    public void delete(Long id) {
        try {
            errorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ErrorNotFoundException();
        }
    }

    public String stach(Long id) {
//        if (getById(id).equals(new ErrorNotFoundException())) {
//            return "O erro escolhido não existe no sistema!";
//        } else {
        ErrorDTO error = getById(id);
        if (error.isArchived()) {
            return "O erro escolhido já está arquivado!";
        } else {
            error.setArchived(true);
            error.setArchivedAt(new Timestamp(System.currentTimeMillis()));
            errorRepository.save(ErrorMapper.toError(error));
            return "Erro arquivado com sucesso!";
        }
    }

    public List<ErrorResumeDTO> search(Environment environment,
                                       Criteria criteria, String term) {
        switch (criteria) {
            case DESCRIPTION:
                return errorRepository.searchByTitle(environment, term);
            case ORIGIN:
                return errorRepository.searchByOrigin(environment, term);
            case LEVEL:
                return errorRepository.searchByLevel(environment,
                                                     Level.valueOf(term));
            default:
                return errorRepository.baseSearch(environment);
        }
    }
}
