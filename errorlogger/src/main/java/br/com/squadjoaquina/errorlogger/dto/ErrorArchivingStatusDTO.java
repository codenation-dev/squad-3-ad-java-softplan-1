package br.com.squadjoaquina.errorlogger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@ApiModel(value = "ErrorArchivingStatus", description = "Status de " +
                                                        "arquivamento de um " +
                                                        "erro.")
@Getter
@Setter
@NoArgsConstructor
public class ErrorArchivingStatusDTO {

    @ApiModelProperty(notes = "Id do erro em questão.")
    private Long id;
    @ApiModelProperty(notes = "Status de arquivamento do erro.")
    private boolean archived;
    @ApiModelProperty(notes = "Data da última operação de arquivamento do " +
                              "erro.")
    private Date lastArchivedDate;
}
