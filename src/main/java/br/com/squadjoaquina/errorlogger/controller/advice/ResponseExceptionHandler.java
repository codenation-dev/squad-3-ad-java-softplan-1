package br.com.squadjoaquina.errorlogger.controller.advice;

import br.com.squadjoaquina.errorlogger.service.exception.ErrorAlreadyArchivedException;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import br.com.squadjoaquina.errorlogger.service.exception.UserEmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = ErrorNotFoundException.class)
    ResponseEntity<Void> handleErrorNotFound() {
        return ResponseEntity.notFound()
                             .build();
    }

    @ExceptionHandler(value = ErrorAlreadyArchivedException.class)
    ResponseEntity<Void> handleErrorNotFound(
            ErrorAlreadyArchivedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .build();
    }

    @ExceptionHandler(value = UserEmailNotFoundException.class)
    ResponseEntity<Void> handleUserEmailNotFound(
            UserEmailNotFoundException e) {
        return ResponseEntity.notFound()
                             .build();
    }

}
