package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.repository.UserRepository;
import br.com.squadjoaquina.errorlogger.service.exception.UserEmailNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private EmailServiceImpl emailService;
    
    private Random random = new Random();

    public void sendNewPassword(String email){
        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new UserEmailNotFoundException(email));
        String newPasswd = newPassword();
        user.setPassword(pe.encode(newPasswd));
        userRepository.save(user);
        emailService.sendNewPasswordEmail(user, newPasswd);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0;i < vet.length; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0){ //gera dígito
            return (char) (random.nextInt(10)+48);
        } else if(opt == 1){ //gera letra maiúscula
            return  (char) (random.nextInt(26)+65);
        } else { //gera letra minúscula
            return (char) (random.nextInt(26)+97);
        }
    }

}
