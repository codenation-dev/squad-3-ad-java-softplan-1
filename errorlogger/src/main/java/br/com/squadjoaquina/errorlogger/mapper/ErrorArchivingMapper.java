package br.com.squadjoaquina.errorlogger.mapper;

import br.com.squadjoaquina.errorlogger.dto.ErrorArchivingDTO;
import br.com.squadjoaquina.errorlogger.model.Error;

public class ErrorArchivingMapper {

    public static ErrorArchivingDTO toDTO(Error error) {
        ErrorArchivingDTO dto = new ErrorArchivingDTO();
        dto.setId(error.getId());
        dto.setArchived(error.isArchived());
        dto.setLastArchivedDate(error.getLastArchivedDate());
        return dto;
    }
}
