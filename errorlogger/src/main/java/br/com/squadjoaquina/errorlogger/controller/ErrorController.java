package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Enumerated;
import javax.validation.Valid;

@RestController
@RequestMapping("v1/error")
public class ErrorController {

    public final ErrorService errorService;

    @Autowired
    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(errorService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveError(@Valid @RequestBody ErrorDTO error) {
        errorService.saveError(error);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public void search(@RequestParam("search_term") String searchTerm,
                       @RequestParam("search_criteria") String searchCriteria,
                       @RequestParam("order_by") String orderBy,
                       @RequestParam(name ="environment")  Environment environment) {
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        errorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/stash")
    public void stash() {
        //idError
        //É o arquivar.
    }
}
