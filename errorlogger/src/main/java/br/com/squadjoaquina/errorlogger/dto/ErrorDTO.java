package br.com.squadjoaquina.errorlogger.dto;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor
public class ErrorDTO {

    @NotNull @Size(min = 1)
    private String origin;

    @NotNull @Size(min = 1)
    private String title;

    private String description;

    @NotNull
    private Long userID;

    @NotNull
    private Level level;

    @NotNull
    private Environment environment;

    private boolean archived = false;

    private Timestamp archivedAt;

    private Timestamp createdAt;
}
