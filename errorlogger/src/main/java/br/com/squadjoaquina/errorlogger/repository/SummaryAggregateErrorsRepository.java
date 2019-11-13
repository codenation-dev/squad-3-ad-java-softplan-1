package br.com.squadjoaquina.errorlogger.repository;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.model.SummaryAggregateErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryAggregateErrorsRepository
        extends JpaRepository<SummaryAggregateErrors, Long> {

    List<SummaryAggregateErrors> findByEnvironment(Environment e);

    List<SummaryAggregateErrors> findByEnvironmentAndLevel(
            Environment environment,
            Level level);

    List<SummaryAggregateErrors> findByEnvironmentAndTitleContainingIgnoreCase(
            Environment environment,
            String title);

    List<SummaryAggregateErrors> findByEnvironmentAndOriginContainingIgnoreCase(
            Environment environment,
            String origin);
}
