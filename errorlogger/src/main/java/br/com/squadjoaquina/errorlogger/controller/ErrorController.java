package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorArchivingDTO;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import br.com.squadjoaquina.errorlogger.service.ErrorAggregateService;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ErrorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(errorService.getById(id), HttpStatus.OK);
    }

    @GetMapping
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

    @PostMapping
    public ResponseEntity<ErrorDTO> save(@Valid @RequestBody ErrorDTO error) {
        return new ResponseEntity<>(errorService.save(error),
                                    HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/archived")
    public ResponseEntity<ErrorArchivingDTO> stash(
            @PathVariable("id") Long id) {
        ErrorArchivingDTO dto = errorService.stash(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        errorService.delete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}
