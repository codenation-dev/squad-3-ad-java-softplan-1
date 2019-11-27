package br.com.squadjoaquina.errorlogger.repository;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Error;
import br.com.squadjoaquina.errorlogger.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {


    @Transactional
    @Modifying
    @Query("DELETE " +
           "FROM Error e " +
           "WHERE " +
           "    e.environment =:environment " +
           "    AND e.level =:level " +
           "    AND e.origin =:origin " +
           "    AND e.title =:title")
    void deleteAggregate(
            @Param("environment") Environment environment,
            @Param("level") Level level,
            @Param("origin") String origin,
            @Param("title") String title);

    @Transactional
    @Modifying
    @Query("UPDATE Error e " +
           "SET e.archivedAt = CURRENT_DATE " +
           "WHERE " +
           "    e.environment =:environment " +
           "    AND e.level =:level " +
           "    AND e.origin =:origin " +
           "    AND e.title =:title")
    void archiveAggregate(
            @Param("environment") Environment environment,
            @Param("level") Level level,
            @Param("origin") String origin,
            @Param("title") String title);

    @Transactional
    @Modifying
    @Query("UPDATE Error e " +
           "SET e.archivedAt = CURRENT_DATE " +
           "WHERE " +
           "    e.id = :id")
    void archive(@Param("id") Long id);
}
