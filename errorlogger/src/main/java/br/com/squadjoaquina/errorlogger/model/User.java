package br.com.squadjoaquina.errorlogger.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
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
