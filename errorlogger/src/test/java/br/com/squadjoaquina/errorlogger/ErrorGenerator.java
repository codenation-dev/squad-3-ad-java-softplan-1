package br.com.squadjoaquina.errorlogger;

public class ErrorGenerator {
    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(6000);
                double random = Math.random();
                if (random < 0.5) {
                    throw new NullPointerException();
                } else {
                    throw new IllegalStateException();
                }
            } catch (InterruptedException | NullPointerException
                    | IllegalStateException e) {
                logError(e);
            }
        }
    }

    private static void logError(Exception e) {
        //PLACEHOLDER PARA O GERADOR DE ERRO DE VERDADE.
        // e.printStackTrace();
    }
}
