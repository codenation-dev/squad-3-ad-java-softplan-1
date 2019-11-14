package br.com.squadjoaquina.errorlogger.mapper;

import br.com.squadjoaquina.errorlogger.dto.ErrorArchivingStatusDTO;
import br.com.squadjoaquina.errorlogger.model.Error;

public class ErrorArchivingStatusMapper {

    public static ErrorArchivingStatusDTO toDTO(Error error) {
        ErrorArchivingStatusDTO dto = new ErrorArchivingStatusDTO();
        dto.setId(error.getId());
        dto.setArchived(error.isArchived());
        dto.setLastArchivedDate(error.getLastArchivedDate());
        return dto;
    }
}
