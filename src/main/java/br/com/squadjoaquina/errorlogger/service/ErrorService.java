package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.mapper.ErrorMapper;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Error;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.repository.ErrorRepository;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorAlreadyArchivedException;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    public void archive(Long id) {
        ErrorDTO errorDTO = getById(id);
        if (errorDTO.getArchivedAt() == null) {
            errorRepository.archive(id);
        } else {
            throw new ErrorAlreadyArchivedException();
        }
    }

    public void archiveAggregate(Environment environment,
                                 Level level,
                                 String origin,
                                 String title) {
        errorRepository.archiveAggregate(
                environment, level, origin, title);

    }

    public void deleteAggregate(Environment environment,
                                Level level,
                                String origin,
                                String title) {
        errorRepository.deleteAggregate(
                environment, level, origin, title);
    }
}
