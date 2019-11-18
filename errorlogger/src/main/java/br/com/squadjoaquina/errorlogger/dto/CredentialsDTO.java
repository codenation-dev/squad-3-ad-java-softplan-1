package br.com.squadjoaquina.errorlogger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;
}
