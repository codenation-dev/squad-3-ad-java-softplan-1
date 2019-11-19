package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.mapper.UserMapper;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.repository.UserRepository;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return UserMapper.toUserDTO(userOptional.get());
        } else {
            throw new ErrorNotFoundException();
        }
    }

    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return UserMapper.toUserDTO(userOptional.get());
        } else {
            throw new ErrorNotFoundException();
        }
    }

    public UserDTO findByLogin(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isPresent()) {
            return UserMapper.toUserDTO(userOptional.get());
        } else {
            throw new ErrorNotFoundException();
        }
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(UserMapper.toUser(userDTO));
        return UserMapper.toUserDTO(user);
    }

    public long count() {
        return userRepository.count();
    }

    public static UserDTO authenticated() {
        try {
            return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}
