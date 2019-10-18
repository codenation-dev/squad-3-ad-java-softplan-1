package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.service.ErrorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/error")
public class ErrorController {


    public final ErrorService errorService;

    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @RequestMapping("/record")
    public void recordError(){
        //String title, String details, String origin, Date date, int user, String level, String environment
    }

    @RequestMapping("/search")
    public void searchErrors(){}

    //Talvez deleteMapping? Os verbos HTTP ainda tem que ser decididos.
    @RequestMapping("/delete")
    public void deleteError() {
        //idError
    }

    @RequestMapping("/stash")
    public void stashError() {
        //idError
        //É o arquivar.
    }
}
