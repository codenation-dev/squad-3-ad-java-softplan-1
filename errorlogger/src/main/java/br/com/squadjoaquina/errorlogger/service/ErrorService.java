package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.dto.ErrorArchivingStatusDTO;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.mapper.ErrorArchivingStatusMapper;
import br.com.squadjoaquina.errorlogger.mapper.ErrorMapper;
import br.com.squadjoaquina.errorlogger.model.Error;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.repository.ErrorRepository;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public ErrorDTO save(ErrorDTO errorDTO) {
        Error savedError = errorRepository.save(ErrorMapper.toError(errorDTO));
        return ErrorMapper.toDTO(savedError);
    }

    public void delete(Long id) {
        try {
            errorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ErrorNotFoundException();
        }
    }

    public ErrorArchivingStatusDTO stash(Long id) {
        ErrorDTO error = getById(id);
        if (error.isArchived()) {
            error.setArchived(false);
        } else {
            error.setArchived(true);
            error.setLastArchivedDate(new Date(System.currentTimeMillis()));
        }
        Error savedError = errorRepository.save(ErrorMapper.toError(error));
        return ErrorArchivingStatusMapper.toDTO(savedError);
    }

    public void stashAggregates(
            List<ErrorAggregate> aggregates) {
    }

    public void deleteAggregates(
            List<ErrorAggregate> aggregates) {
    }
}
