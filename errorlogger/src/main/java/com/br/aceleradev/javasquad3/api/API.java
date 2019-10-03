package com.br.aceleradev.javasquad3.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class API {

    /**
     * Insere um novo erro na base
     *
     * @param title       título do erro
     * @param title       descrição do erro
     * @param origin      serviço de origem do erro (ip ou nome?)
     * @param date        data de ocorrência do erro
     * @param user        user que registrou o erro
     * @param level       level do erro [debug, warning, error]
     * @param environment ambiente onde ocorreu o erro [produção,
     *                    homologação, dev
     * @return ok http status
     **/
    private boolean newError(String title, String description, String origin,
                             Date date, int user, String level,
                             String environment) {
        return true;
    }


    /**
     * Procura por erros na base
     *
     * @param searchCriteria campo/critério de busca do erro [level, origem,
     *                       descrição]
     * @param searchValue    valor string a ser buscado
     * @param orderBy        critério de ordenação do erro [level, frequência]
     * @return Lista contendo agregados de erros (agregados são uma conjunto
     * de erros iguais, representado pelo sumário de um erro [campos de
     * exibição na lista] e por uma quantidade de erros deste tipo)
     **/
    private List<Error> searchErrors(String searchCriteria, String searchValue,
                                     String orderBy) {

        //Deve retornar agregados de erros, e não objetos individuais do tipo
        // erro.
        return new ArrayList<Error>();
    }

    /**
     * retorna um erro da base
     *
     * @param errorId  id do erro a ser retornado
     * @return retorna entidade erro que corresponde ao ID. Se não houver,
     * retorna status http erro ou não encontrado.
     **/
    private Error getError(int errorId) {
        return new Error();
    }


    /**
     * deleta um erro da base
     *
     * @param errorId  id do erro a ser deletado
     * @return retorna status http ok ou erro.
     **/
    private void deleteError(int errorId) {
    }


    /**
     * arquiva um erro da base
     *
     * @param errorId  id do erro a ser arquivado
     * @return retorna status http ok ou erro.
     **/
    private void archievError(int errorId) {
    }




}
