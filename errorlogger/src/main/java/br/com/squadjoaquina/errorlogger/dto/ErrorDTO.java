package br.com.squadjoaquina.errorlogger.dto;

import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("Error")
@Getter
@Setter
@NoArgsConstructor
public class ErrorDTO {

    @ApiModelProperty(notes = "Id do erro gerado pela base de dados.")
    private Long id;

    @ApiModelProperty(notes = "Aplicação que deu origem ao erro.")
    @NotBlank
    private String origin;

    @ApiModelProperty(notes = "Título do erro.\n" +
                              "Recomenda-se a utilização do nome " +
                              "da Exception (ou equivalente).")
    @NotBlank
    private String title;

    @ApiModelProperty(notes = "Descrição do erro\n" +
                              "Recomenda-se a utilização do stacktrace " +
                              "(ou equivalente).")
    private String description;

    @ApiModelProperty(notes = "Id do User que registrou o erro.")
    @NotNull
    private Long userID;

    @ApiModelProperty(notes = "Level de severidade do erro")
    @NotNull
    private Level level;

    @ApiModelProperty(notes = "Ambiente no qual ocorreu o erro.")
    @NotNull
    private Environment environment;

    @ApiModelProperty(notes = "Status de arquivação do erro.")
    private boolean archived;

    @ApiModelProperty(notes = "Última data de arquivação do erro.")
    private Date lastArchivedDate;

    @ApiModelProperty(notes = "Data de criação do erro. Imutável.")
    private Date createdAt;
}
