package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.mapper.UserMapper;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.repository.UserRepository;
import br.com.squadjoaquina.errorlogger.service.exception.ErrorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

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

    public UserDTO findByName(String name) {
        Optional<User> userOptional = userRepository.findByName(name);
        if (userOptional.isPresent()) {
            return UserMapper.toUserDTO(userOptional.get());
        } else {
            throw new ErrorNotFoundException();
        }
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setPassword(pe.encode(userDTO.getPassword()));
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
