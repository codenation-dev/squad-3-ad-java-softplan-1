package br.com.squadjoaquina.errorlogger.service.exception;

public class ErrorNotFoundException extends RuntimeException {
    public ErrorNotFoundException() {
    }

    public ErrorNotFoundException(String message) {
        super(message);
    }
}
