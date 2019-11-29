package br.com.squadjoaquina.errorlogger.service.exception;

import org.hibernate.ObjectNotFoundException;

import java.io.Serializable;

public class UserEmailNotFoundException extends ObjectNotFoundException {

    public UserEmailNotFoundException(Serializable object) {
        super(object, "E-mail não encontrado");
    }
}
