package br.com.squadjoaquina.errorlogger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Immutable
@Subselect("SELECT" +
           "    e.environment as environment," +
           "    e.level as level," +
           "    e.origin as origin," +
           "    e.title as title," +
           "    MAX(e.id) as last_error_id," +
           "    MAX(e.created_at) as last_error_date," +
           "    COUNT(e.id) as events " +
           "FROM error e " +
           "GROUP BY " +
           "    e.environment, " +
           "    e.level, " +
           "    e.origin, " +
           "    e.title")
public class ErrorAggregate {

    @Enumerated(EnumType.STRING)
    private Level level;

    private String origin;

    private String title;

    @Enumerated(EnumType.STRING)
    private Environment environment;

    //A Id is always necessary for a jpa entity.
    // This seems the most suitable field for it.
    @Id
    private Long lastErrorId;

    private Date lastErrorDate;

    private Long events;

}
