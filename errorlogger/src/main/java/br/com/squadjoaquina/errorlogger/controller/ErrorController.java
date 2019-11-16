package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.service.ErrorAggregateService;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/error")
public class ErrorController {

    public final ErrorService errorService;
    public final ErrorAggregateService errorAggregateService;

    @Autowired
    public ErrorController(ErrorService errorService,
                           ErrorAggregateService aggregateErrorsService) {
        this.errorService = errorService;
        this.errorAggregateService = aggregateErrorsService;
    }


    @ApiOperation(value = "Retorna um erro pelo seu id",
            response = ErrorDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Erro retornado com sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetro inválido"),
            @ApiResponse(code = 401,
                    message = "Você não possui autorização para utilizar este" +
                              " recurso."),
            @ApiResponse(code = 404, message = "Erro não encontrado.")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ErrorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(errorService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna páginas de agregados de erros",
            response = ErrorAggregate.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Agregado de erros retornado com sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inváidos."),
            @ApiResponse(code = 401,
                    message = "Você não possui autorização para utilizar este" +
                              " recurso.")
    })
    @GetMapping(value = "/aggregates",produces = "application/json")
    public ResponseEntity<Page<ErrorAggregate>> searchAggregates(
            @RequestParam(value = "environment", required = false) Environment environment,
            @RequestParam(value = "level", required = false) Level level,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "title", required = false) String title,
            Pageable pageable) {


        return new ResponseEntity<>(
                errorAggregateService.search(environment,
                                             level,
                                             origin,
                                             title,
                                             pageable),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Registra um novo erro",
            response = ErrorAggregate.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Erro registrado com sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inváidos."),
            @ApiResponse(code = 401,
                    message = "Você não possui autorização para utilizar este" +
                              " recurso.")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<ErrorDTO> save(@Valid @RequestBody ErrorDTO error) {
        return new ResponseEntity<>(errorService.save(error),
                                    HttpStatus.CREATED);
    }

    @ApiOperation(value = "Arquiva um erro")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Erro arquivado sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inváidos."),
            @ApiResponse(code = 401,
                    message = "Você não possui autorização para utilizar este" +
                              " recurso."),
            @ApiResponse(code = 404, message = "Erro não encontrado."),
            @ApiResponse(code = 409, message = "Estado conflitante: O erro já" +
                                               " se " +
                                               "encontra " +
                                               "arquivado.")
    })
    @PatchMapping(value = "/{id}/archived", produces = "application/json")
    public ResponseEntity<Void> archive(
            @PathVariable("id") Long id) {
        errorService.archive(id);
        return ResponseEntity.noContent()
                             .build();
    }

    @ApiOperation(value = "Exclui (irreversivelmente) um erro.")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Erro excluído com sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inváidos."),
            @ApiResponse(code = 401,
                    message = "Você não possui autorização para utilizar este" +
                              " recurso."),
            @ApiResponse(code = 404, message = "Erro não encontrado.")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        errorService.delete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}
