package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.service.ErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/error")
public class ErrorController {


    public final ErrorService errorService;

    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @RequestMapping("/save")
    public void save() {
        //String title, String details, String origin, Date date, int user,
        // String level, String environment
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
