package br.com.squadjoaquina.errorlogger.dto;

import br.com.squadjoaquina.errorlogger.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements UserDetails {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Date createdAt;

    public UserDTO(User user) {
        email = user.getEmail();
        password = user.getPassword();
        login = user.getLogin();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
