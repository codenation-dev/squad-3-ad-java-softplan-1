package br.com.squadjoaquina.errorlogger.dto;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class ErrorResumeDTO {

    private Level level;

    private String origin;

    private String title;

    private Long lastErrorId;

    private Date lastErrorDate;

    private Long Events;
}
