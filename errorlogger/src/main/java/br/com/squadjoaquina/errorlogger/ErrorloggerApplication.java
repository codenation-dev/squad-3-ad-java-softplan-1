package br.com.squadjoaquina.errorlogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ErrorloggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorloggerApplication.class, args);
		//ALTERACAO MANERA
	}
	//Comentario mais maneiro
}
