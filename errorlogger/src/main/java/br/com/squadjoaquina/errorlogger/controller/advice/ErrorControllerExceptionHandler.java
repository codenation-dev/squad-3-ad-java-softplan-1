package br.com.squadjoaquina.errorlogger.controller.advice;

import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerExceptionHandler {

    @ExceptionHandler(value = ErrorNotFoundException.class)
    ResponseEntity<Void> handleErrorNotFound() {
        return ResponseEntity.notFound()
                             .build();
    }

}
