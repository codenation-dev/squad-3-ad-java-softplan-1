package br.com.squadjoaquina.errorlogger.controller.impl;

import br.com.squadjoaquina.errorlogger.controller.UserController;
import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @PostMapping(produces = "application/json")
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(userDTO),
                                    HttpStatus.CREATED);
    }
}
