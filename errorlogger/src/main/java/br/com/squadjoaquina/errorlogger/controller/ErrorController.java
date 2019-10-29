package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> saveError(@Valid @RequestBody ErrorDTO error){
        errorService.saveError(error);
        return new ResponseEntity<>("Erro salvo com sucesso!", HttpStatus.OK);
    }

    @RequestMapping("/search")
    public void search(){}

    //Talvez deleteMapping? Os verbos HTTP ainda tem que ser decididos.
    @RequestMapping("/delete")
    public void delete() {
        //idError
    }

    @RequestMapping("/stash/{id}")
    public ResponseEntity<?> stash(@PathVariable Long id) {
        errorService.stashError(id);
        return ResponseEntity.notFound().build();
    }
}
