package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/error")
public class ErrorController {

    public final ErrorService errorService;

    @Autowired
    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveError(@Valid @RequestBody ErrorDTO error) {
        errorService.saveError(error);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/search")
    public void search() {
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        errorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/stash")
    public void stash() {
        //idError
        //É o arquivar.
    }
}
