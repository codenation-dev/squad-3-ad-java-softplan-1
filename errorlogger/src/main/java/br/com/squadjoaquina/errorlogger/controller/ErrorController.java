package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.service.ErrorService;
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

    //TODO: Adicionar tratamento de exceção para quando o id recebido não
    // corresponder a nenhum erro na base.
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        errorService.delete(id);
    }


    @RequestMapping("/stash")
    public void stash() {
        //idError
        //É o arquivar.
    }
}
