package br.com.squadjoaquina.errorlogger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Email
    @NotNull
    private String email;


    @NotNull
    private String login;


    @NotNull
    private String password;

}
