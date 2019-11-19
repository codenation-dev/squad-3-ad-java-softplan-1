package br.com.squadjoaquina.errorlogger.controller.impl;

import br.com.squadjoaquina.errorlogger.controller.ErrorController;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.service.ErrorAggregateService;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/error")
public class ErrorControllerImpl implements ErrorController {

    public final ErrorService errorService;
    public final ErrorAggregateService errorAggregateService;

    @Autowired
    public ErrorControllerImpl(ErrorService errorService,
                               ErrorAggregateService aggregateErrorsService) {
        this.errorService = errorService;
        this.errorAggregateService = aggregateErrorsService;
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ErrorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(errorService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/aggregates", produces = "application/json")
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


    @PostMapping(produces = "application/json")
    public ResponseEntity<ErrorDTO> save(@Valid @RequestBody ErrorDTO error) {
        return new ResponseEntity<>(errorService.save(error),
                                    HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}/archived", produces = "application/json")
    public ResponseEntity<Void> archive(
            @PathVariable("id") Long id) {
        errorService.archive(id);
        return ResponseEntity.noContent()
                             .build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        errorService.delete(id);
        return ResponseEntity.noContent()
                             .build();
    }

    @PatchMapping(value = "/aggregates/archived", produces = "application/json")
    public ResponseEntity<Integer> archiveAggregate(
            @RequestParam(value = "environment", required = true) Environment environment,
            @RequestParam(value = "level", required = true) Level level,
            @RequestParam(value = "origin", required = true) String origin,
            @RequestParam(value = "title", required = true) String title
    ) {
        errorService.archiveAggregate(environment, level, origin, title);
        return ResponseEntity.noContent()
                             .build();
    }

    @DeleteMapping(value = "/aggregates", produces = "application/json")
    public ResponseEntity<Void> deleteAggregate(
            @RequestParam(value = "environment", required = true) Environment environment,
            @RequestParam(value = "level", required = true) Level level,
            @RequestParam(value = "origin", required = true) String origin,
            @RequestParam(value = "title", required = true) String title) {
        errorService.deleteAggregate(environment, level, origin, title);
        return ResponseEntity.noContent()
                             .build();
    }

}
