package com.br.aceleradev.javasquad3.entitys;

import java.util.Date;

public class API {

    private boolean newError(String title, String details, String origin, Date date, int user, String level, String environment) {
        return true; //Retornar se deu certo o request.
    }

    private void deleteError(int idError){};
    private void archievError(int idError){};

    private String[] listErrors() {};
    private String getError(){return "error";};


}
