package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.repository.ErrorAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ErrorAggregateService {

    private final ErrorAggregateRepository repository;

    @Autowired
    public ErrorAggregateService(
            ErrorAggregateRepository repository) {
        this.repository = repository;
    }

    public Page<ErrorAggregate> search(
            Specification<ErrorAggregate> errorAggregateSpec,
            Pageable pageable) {

        return repository.findAll(errorAggregateSpec, pageable);

    }


}
