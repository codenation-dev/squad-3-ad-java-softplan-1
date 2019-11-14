package br.com.squadjoaquina.errorlogger.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ErrorArchivingDTO {
    private Long id;
    private boolean archived;
    private Timestamp lastArchivedDate;
}
