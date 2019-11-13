package br.com.squadjoaquina.errorlogger.repository;

import br.com.squadjoaquina.errorlogger.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {
}
