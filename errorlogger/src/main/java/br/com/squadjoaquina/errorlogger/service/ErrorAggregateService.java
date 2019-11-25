package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.repository.ErrorAggregateRepository;
import br.com.squadjoaquina.errorlogger.repository.specs.ErrorAggregateSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ErrorAggregateService {

    private final ErrorAggregateRepository errorAggregateRepository;

    @Autowired
    public ErrorAggregateService(
            ErrorAggregateRepository repository) {
        this.errorAggregateRepository = repository;
    }

    public Page<ErrorAggregate> search(Environment environment,
                                       Level level,
                                       String origin,
                                       String title,
                                       Pageable pageable) {

        Specification<ErrorAggregate> errorAggregateSpecs = buildSpecs(
                environment, level, origin, title);
        return errorAggregateRepository.findAll(errorAggregateSpecs, pageable);

    }

    private Specification<ErrorAggregate> buildSpecs(Environment environment,
                                                     Level level,
                                                     String origin,
                                                     String title) {
        Specification<ErrorAggregate> environmentSpec =
                ErrorAggregateSpecs.environmentEquals(environment);
        Specification<ErrorAggregate> levelSpec =
                ErrorAggregateSpecs.levelEquals(level);
        Specification<ErrorAggregate> originSpec =
                ErrorAggregateSpecs.originLike(origin);
        Specification<ErrorAggregate> titleSpec =
                ErrorAggregateSpecs.titleLike(title);

        return environmentSpec.and(levelSpec.and(originSpec.and(titleSpec)));
    }


}
