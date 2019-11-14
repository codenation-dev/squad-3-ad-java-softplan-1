package br.com.squadjoaquina.errorlogger.controller.advice;

import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import com.google.gson.Gson;
import net.kaczmarzyk.spring.data.jpa.utils.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorControllerExceptionHandler {

    @ExceptionHandler(value = ErrorNotFoundException.class)
    ResponseEntity<Void> handleErrorNotFound() {
        return ResponseEntity.notFound()
                             .build();
    }

    @ExceptionHandler(value = Converter.ValueRejectedException.class)
    ResponseEntity<String> handleConverterException(
            Converter.ValueRejectedException exception) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = exception.getClass()
                                  .getSimpleName() +
                         ": " +
                         exception.getMessage();

        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", System.currentTimeMillis());
        map.put("status", status.value());
        map.put("error", status.getReasonPhrase());
        map.put("message", message);


        return new ResponseEntity<>(new Gson().toJson(map), status);
    }

}
