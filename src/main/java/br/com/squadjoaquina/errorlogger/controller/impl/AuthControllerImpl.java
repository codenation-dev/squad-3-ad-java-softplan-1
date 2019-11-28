package br.com.squadjoaquina.errorlogger.controller.impl;

import br.com.squadjoaquina.errorlogger.controller.AuthController;
import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.security.JWTUtil;
import br.com.squadjoaquina.errorlogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthControllerImpl implements AuthController {

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserDTO user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent()
                             .build();
    }


}