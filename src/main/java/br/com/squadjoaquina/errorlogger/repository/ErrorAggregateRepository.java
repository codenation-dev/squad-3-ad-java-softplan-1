package br.com.squadjoaquina.errorlogger.repository;

import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorAggregateRepository
        extends JpaRepository<ErrorAggregate, Long> {

    Page<ErrorAggregate> findAll(
            Specification<ErrorAggregate> errorAggregateSpec,
            Pageable pageable);
}
