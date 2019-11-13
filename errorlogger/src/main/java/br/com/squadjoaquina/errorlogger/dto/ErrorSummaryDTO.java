package br.com.squadjoaquina.errorlogger.dto;

import br.com.squadjoaquina.errorlogger.model.Level;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorSummaryDTO {

    private Level level;

    private String origin;

    private String title;

    private Long idLastError;

    private Date createdAt;

    private Long events;

    public ErrorSummaryDTO(Level level, String origin, String title,
                           Long idLastError, Date createdAt, Long events) {
        this.level = level;
        this.origin = origin;
        this.title = title;
        this.idLastError = idLastError;
        this.createdAt = createdAt;
        this.events = events;
    }
}
