package br.com.aceleradev.javasquad3.errorlogger.repository;

import br.com.aceleradev.javasquad3.errorlogger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
