package br.com.squadjoaquina.errorlogger.security;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.mapper.UserMapper;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<User> {


    private final UserService userService;

    @Autowired
    public SecurityAuditorAware(
            UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();

        if (authentication == null ||
            authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserDTO userDto = userService.findByEmail(
                (((UserDTO) authentication.getPrincipal()).getEmail()));
        return Optional.of(UserMapper.toUser(userDto));
    }
}