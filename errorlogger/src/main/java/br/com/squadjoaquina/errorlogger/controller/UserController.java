package br.com.squadjoaquina.errorlogger.controller;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.model.ErrorAggregate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "User Controller",
        description = "Realiza operações com Usuários.")
public interface UserController {

    @ApiOperation(value = "Registra um novo usuário",
            response = ErrorAggregate.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = ResponseMessages.CREATED_201),
            @ApiResponse(code = 400,
                    message = ResponseMessages.BAD_REQUEST_400),
            @ApiResponse(code = 401,
                    message = ResponseMessages.UNAUTHORIZED_401),
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> save(UserDTO userDTO);

}
