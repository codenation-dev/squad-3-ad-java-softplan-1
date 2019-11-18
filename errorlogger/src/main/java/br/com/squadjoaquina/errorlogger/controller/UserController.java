package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @Autowired
     private BCryptPasswordEncoder pe;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> save(@Valid @RequestBody User user){
        user.setPassword(pe.encode(user.getPassword()));
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userService.save(userDTO), HttpStatus.OK);
    }
}
