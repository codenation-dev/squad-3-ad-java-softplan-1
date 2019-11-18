package br.com.squadjoaquina.errorlogger.controller;

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
public class UserController {

    private final UserService userService;
    @Autowired
    private BCryptPasswordEncoder pe;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setPassword(pe.encode(userDTO.getPassword()));
        return new ResponseEntity<>(userService.save(userDTO),
                                    HttpStatus.CREATED);
    }
}
