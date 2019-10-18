package br.com.squadjoaquina.errorlogger.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Email
    @NotNull
    private String email;

    @Getter
    @Setter
    @NotNull
    private String login;

    @Getter
    @Setter
    @NotNull
    private String password;

    public User() {
    }
}
