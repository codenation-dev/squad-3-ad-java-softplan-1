package br.com.squadjoaquina.errorlogger.dto;

import br.com.squadjoaquina.errorlogger.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements UserDetails {

    private Long id;

    @Email
    @NotNull
    private String email;


    @NotNull
    private String login;


    @NotNull
    private String password;

    @CreatedDate
    private Timestamp createdAt;

    @Override
    public
    Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public UserDTO(User user){
        email = user.getEmail();
        password = user.getPassword();
        login = user.getLogin();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public
    boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public
    boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public
    boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public
    boolean isEnabled() {
        return true;
    }
}