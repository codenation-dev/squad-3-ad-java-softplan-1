package br.com.squadjoaquina.errorlogger.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@ApiModel(description = "Agregado de erros do mesmo tipo. \n" +
                        "Dois erros são considerados do mesmo tipo quando " +
                        "eles tem os mesmos valores de: enviornment, level, " +
                        "origin e title.")
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
           "WHERE " +
           "    e.archived_at IS NULL " +
           "GROUP BY " +
           "    e.environment, " +
           "    e.level, " +
           "    e.origin, " +
           "    e.title")
public class ErrorAggregate {

    @ApiModelProperty(notes = "Level de severidade do erro")
    @Enumerated(EnumType.STRING)
    private Level level;

    @ApiModelProperty(notes = "Aplicação que deu origem ao erro.")
    private String origin;

    @ApiModelProperty(notes = "Título do erro.")
    private String title;

    @ApiModelProperty(notes = "Ambiente no qual ocorreu o erro.")
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @ApiModelProperty(notes = "Id do último erro deste tipo ocorrido.")
    //A JPA entity must have an Id.
    //This seems the most suitable field for it.
    @Id
    private Long lastErrorId;

    @ApiModelProperty(notes = "Data de ocorrência do último erro deste tipo")
    @JsonFormat(pattern = "dd-MM-yyyy H:mm:ss")
    private LocalDateTime lastErrorDate;

    @ApiModelProperty(notes = "Número de erros deste tipo registrados.")
    private Long events;

}
