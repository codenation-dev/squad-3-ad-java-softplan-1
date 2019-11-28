package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.EmailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface AuthController {

    @ApiIgnore
    ResponseEntity<Void> refreshToken(HttpServletResponse response);

    ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO);
}