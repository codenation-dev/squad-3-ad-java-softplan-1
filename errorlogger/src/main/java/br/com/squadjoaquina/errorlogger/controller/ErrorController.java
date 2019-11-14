package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorArchivingStatusDTO;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.service.ErrorAggregateService;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.OnTypeMismatch;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ErrorAggregate>> search(
            @And({
                    @Spec(path = "environment", spec = Equal.class,
                            onTypeMismatch = OnTypeMismatch.EXCEPTION),
                    @Spec(path = "level", spec = Equal.class,
                            onTypeMismatch = OnTypeMismatch.EXCEPTION),
                    @Spec(path = "origin", spec = LikeIgnoreCase.class),
                    @Spec(path = "title", spec = LikeIgnoreCase.class)
            }) Specification<ErrorAggregate> errorAggregateSpec,
            Pageable pageable) {

        return new ResponseEntity<>(
                errorAggregateService.search(errorAggregateSpec, pageable),
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

    @ApiOperation(value = "Arquiva ou desarquiva um erro",
            response = ErrorArchivingStatusDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Erro arquivo ou desarquivado com sucesso."),
            @ApiResponse(code = 400,
                    message = "A requisição enviada possui parâmetros " +
                              "inváidos."),
            @ApiResponse(code = 401,
                    message = "Você não possui autorização para utilizar este" +
                              " recurso."),
            @ApiResponse(code = 404, message = "Erro não encontrado.")
    })
    @PatchMapping(value = "/{id}/archived", produces = "application/json")
    public ResponseEntity<ErrorArchivingStatusDTO> stash(
            @PathVariable("id") Long id) {
        ErrorArchivingStatusDTO dto = errorService.stash(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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
