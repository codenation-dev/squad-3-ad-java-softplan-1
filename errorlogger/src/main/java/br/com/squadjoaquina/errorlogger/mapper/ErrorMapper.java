package br.com.squadjoaquina.errorlogger.mapper;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Error;

public class ErrorMapper {

    private ErrorMapper(){
        super();
    }

    public static Error toError (ErrorDTO errorDTO){
        Error error = new Error();

        error.setArchived(errorDTO.isArchived());
        error.setCreatedAt(errorDTO.getCreatedAt());
        error.setDescription(errorDTO.getDescription());
        error.setEnvironment(errorDTO.getEnvironment());
        error.setLevel(errorDTO.getLevel());
        error.setOrigin(errorDTO.getOrigin());
        error.setTitle(errorDTO.getTitle());
        error.setUserID(errorDTO.getUserID());
        error.setArchivedAt(errorDTO.getArchivedAt());
        return error;
    }
}
