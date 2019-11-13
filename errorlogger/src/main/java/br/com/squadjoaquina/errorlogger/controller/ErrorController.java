package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.controller.paramenum.Criteria;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import br.com.squadjoaquina.errorlogger.service.ErrorAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ErrorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(errorService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody ErrorDTO error) {
        return new ResponseEntity<>(errorService.save(error), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ErrorAggregate>> search(
            @RequestParam(value = "environment") Environment environment,
            @RequestParam(value = "criteria", required = false, defaultValue
                    = "NOT_REQUESTED")
                    Criteria criteria,
            @RequestParam(value = "term", required = false)
                    String term)
            throws MissingServletRequestParameterException {


        if (criteria != null &&
            !criteria.equals(Criteria.NOT_REQUESTED) &&
            (term == null || term.isEmpty())) {
            throw new MissingServletRequestParameterException("term",
                                                              String.class.getSimpleName());
        }

        if ((term != null && !term.isEmpty()) &&
            (criteria == null || criteria.equals(Criteria.NOT_REQUESTED))) {
            throw new MissingServletRequestParameterException("criteria",
                                                              Criteria.class.getSimpleName());

        }
        return new ResponseEntity<>(errorAggregateService.search(environment,
                                                                 criteria,
                                                                 term),
                                    HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        errorService.delete(id);
        return ResponseEntity.noContent()
                             .build();
    }

    @RequestMapping("/stash/{id}")
    public ResponseEntity<String> stash(@PathVariable Long id) {
        return new ResponseEntity<>(errorService.stach(id), HttpStatus.OK);
    }
}
