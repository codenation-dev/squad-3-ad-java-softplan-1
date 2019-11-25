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

@Api(tags = "Error Controller",
        description = "Realiza operações com Erros e Agregados de Erros.")
public interface ErrorController {

    @ApiOperation(value = "Retorna um erro a partir do Id do mesmo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = ResponseMessages.OK_200),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401),
            @ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
    })
    public ResponseEntity<ErrorDTO> getById(Long id);

    @ApiOperation(value = "Retorna páginas de agregados de erros. Permite " +
                          "diversos tipos de filtragem.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = ResponseMessages.OK_200),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401)
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
                    message = ResponseMessages.CREATED_201),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401),
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ErrorDTO> save(ErrorDTO error);

    @ApiOperation(value = "Arquiva um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = ResponseMessages.NO_CONTENT_204),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401),
            @ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404),
            @ApiResponse(code = 409, message = ResponseMessages.CONFLICT_409)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> archive(Long id);

    @ApiOperation(value = "Exclui (irreversivelmente) um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = ResponseMessages.NO_CONTENT_204),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401),
            @ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404),
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(Long id);

    @ApiOperation(value = "Arquiva um agregado de erros.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = ResponseMessages.NO_CONTENT_204),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401)
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
                    message = ResponseMessages.NO_CONTENT_204),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAggregate(
            Environment environment,
            Level level,
            String origin,
            String title);
}

