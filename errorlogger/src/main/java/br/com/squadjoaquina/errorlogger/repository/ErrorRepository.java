package br.com.squadjoaquina.errorlogger.repository;

import br.com.squadjoaquina.errorlogger.dto.ErrorResumeDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Error;
import br.com.squadjoaquina.errorlogger.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {


    @Query("SELECT " +
           "NEW br.com.squadjoaquina.errorlogger.dto.ErrorResumeDTO" +
           "(" +
           "e.level, " +
           "e.origin, " +
           "e.title, " +
           "MAX(e.id), " +
           "MAX(e.createdAt), " +
           "COUNT(e.id)" +
           ") " +
           "FROM Error e " +
           "WHERE e.environment =:environment " +
           "GROUP BY e.level, e.origin, e.title")
    List<ErrorResumeDTO> baseSearch(
            @Param("environment") Environment environment);

    @Query("SELECT " +
           "NEW br.com.squadjoaquina.errorlogger.dto.ErrorResumeDTO" +
           "(" +
           "e.level, " +
           "e.origin, " +
           "e.title, " +
           "MAX(e.id), " +
           "MAX(e.createdAt), " +
           "COUNT(e.id)" +
           ") " +
           "FROM Error e " +
           "WHERE e.environment =:environment " +
           "AND e.origin LIKE %:searchTerm% " +
           "GROUP BY e.level, e.origin, e.title")
    List<ErrorResumeDTO> searchByOrigin(
            @Param("environment") Environment environment,
            @Param("searchTerm") String searchTerm);

    @Query("SELECT " +
           "NEW br.com.squadjoaquina.errorlogger.dto.ErrorResumeDTO" +
           "(" +
           "e.level, " +
           "e.origin, " +
           "e.title, " +
           "MAX(e.id), " +
           "MAX(e.createdAt), " +
           "COUNT(e.id)" +
           ") " +
           "FROM Error e " +
           "WHERE e.environment =:environment " +
           "AND e.title LIKE %:searchTerm% " +
           "GROUP BY e.level, e.origin, e.title")
    List<ErrorResumeDTO> searchByTitle(
            @Param("environment") Environment environment,
            @Param("searchTerm") String searchTerm);

    //Adicionar sort
    @Query("SELECT " +
           "NEW br.com.squadjoaquina.errorlogger.dto.ErrorResumeDTO" +
           "(" +
           "e.level, " +
           "e.origin, " +
           "e.title, " +
           "MAX(e.id), " +
           "MAX(e.createdAt), " +
           "COUNT(e.id)" +
           ") " +
           "FROM Error e " +
           "WHERE e.environment =:environment " +
           "AND e.level =:searchTerm " +
           "GROUP BY e.level, e.origin, e.title")
    List<ErrorResumeDTO> searchByLevel(
            @Param("environment") Environment environment,
            @Param("searchTerm") Level level);


}
