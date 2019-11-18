package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.model.Level;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Error Endpoint",
        description = "Realiza operações com erros e agregados de erros.")
public interface ErrorController {

    @ApiOperation(value = "Arquiva um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso."),
            @ApiResponse(code = 404, message = "Não encontrado.")
    })
    public ResponseEntity<ErrorDTO> getById(Long id);

    @ApiOperation(value = "Arquiva um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso.")
    })
    public ResponseEntity<Page<ErrorAggregate>> searchAggregates(
            Environment environment,
            Level level,
            String origin,
            String title,
            Pageable pageable
    );

    @ApiOperation(value = "Registra um novo erro",
            response = ErrorAggregate.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Criado."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso."),
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ErrorDTO> save(ErrorDTO error);

    @ApiOperation(value = "Arquiva um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message =
                            "Sucesso. Não há conteúdo adicional a ser enviado" +
                            " " +
                            "no corpo da resposta."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso."),
            @ApiResponse(code = 404, message = "Não encontrado."),
            @ApiResponse(code = 409, message =
                    "Conflito. A requisição pressupõe " +
                    "que o recurso está em um estado " +
                    "diferente do estado verificado no" +
                    " servidor.")
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> archive(Long id);

    @ApiOperation(value = "Exclui (irreversivelmente) um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message =
                            "Sucesso. Não há conteúdo adicional a ser enviado" +
                            " " +
                            "no corpo da resposta."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso."),
            @ApiResponse(code = 404, message = "Não encontrado."),
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(Long id);

    @ApiOperation(value = "Arquiva um agregado de erros.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message =
                            "Sucesso. Não há conteúdo adicional a ser enviado" +
                            " " +
                            "no corpo da resposta."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso.")
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Integer> archiveAggregate(
            Environment environment,
            Level level,
            String origin,
            String title
    );

    @ApiOperation(value = "Exclui (irreversivelmente) um agregado de erros.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message =
                            "Sucesso. Não há conteúdo adicional a ser enviado" +
                            " " +
                            "no corpo da resposta."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inválidos."),
            @ApiResponse(code = 401,
                    message =
                            "Você não possui autorização para utilizar este " +
                            "recurso.")
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAggregate(
            Environment environment,
            Level level,
            String origin,
            String title);
}

