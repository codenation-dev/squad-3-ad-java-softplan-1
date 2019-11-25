package br.com.squadjoaquina.errorlogger.controller;

public class ResponseMessages {

    public static final String OK_200 = "A requisição foi bem sucedida";

    public static final String CREATED_201 = "Criado.";

    public static final String NO_CONTENT_204 = "Sucesso. Não há conteúdo " +
                                                "adicional a ser enviado no " +
                                                "corpo da resposta.";

    public static final String BAD_REQUEST_400 = "A requisição enviada possui" +
                                                 " parâmetros inválidos.";

    public static final String UNAUTHORIZED_401 = "Você não possui " +
                                                  "autorização para utilizar " +
                                                  "este recurso.";

    public static final String NOT_FOUND_404 = "Não encontrado.";

    public static final String CONFLICT_409 = "Conflito. A requisição " +
                                              "pressupõe que o recurso está " +
                                              "em um estado diferente do " +
                                              "estado verificado no  servidor.";
}
