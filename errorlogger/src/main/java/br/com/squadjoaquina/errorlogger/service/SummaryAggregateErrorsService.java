package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.controller.paramenum.Criteria;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.model.SummaryAggregateErrors;
import br.com.squadjoaquina.errorlogger.repository.SummaryAggregateErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryAggregateErrorsService {

    private final SummaryAggregateErrorsRepository repository;

    @Autowired
    public SummaryAggregateErrorsService(
            SummaryAggregateErrorsRepository repository) {
        this.repository = repository;
    }

    //Isso aqui ainda não está 100%, seria melhor implementar com predicates.
    public List<SummaryAggregateErrors> search(Environment environment,
                                               Criteria criteria, String term) {

        switch (criteria) {
            case DESCRIPTION:
                return repository.findByEnvironmentAndTitleContainingIgnoreCase(
                        environment, term);
            case ORIGIN:
                return repository.findByEnvironmentAndOriginContainingIgnoreCase(
                        environment, term);
            case LEVEL:
                return repository.findByEnvironmentAndLevel(environment,
                                                            Level.valueOf(term));
            default:
                return repository.findByEnvironment(environment);
        }
    }


}
