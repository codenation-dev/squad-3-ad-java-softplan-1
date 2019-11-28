package br.com.squadjoaquina.errorlogger.controller;

import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

public interface AuthController {

    @ApiIgnore
    ResponseEntity<Void> refreshToken(HttpServletResponse response);

}