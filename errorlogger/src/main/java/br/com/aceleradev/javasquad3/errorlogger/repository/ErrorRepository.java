package br.com.aceleradev.javasquad3.errorlogger.repository;

import br.com.aceleradev.javasquad3.errorlogger.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {
}
