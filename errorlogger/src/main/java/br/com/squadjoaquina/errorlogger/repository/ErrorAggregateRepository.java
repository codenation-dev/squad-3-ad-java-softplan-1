package br.com.squadjoaquina.errorlogger.repository;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorAggregateRepository
        extends JpaRepository<ErrorAggregate, Long> {

    List<ErrorAggregate> findByEnvironment(Environment e);

    List<ErrorAggregate> findByEnvironmentAndLevel(
            Environment environment,
            Level level);

    List<ErrorAggregate> findByEnvironmentAndTitleContainingIgnoreCase(
            Environment environment,
            String title);

    List<ErrorAggregate> findByEnvironmentAndOriginContainingIgnoreCase(
            Environment environment,
            String origin);
}
