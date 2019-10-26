package br.com.squadjoaquina.errorlogger.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Error {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotEmpty
    private String origin;

    @NotEmpty
    private String title;

    private String description;

    //TODO: Add association to another class.
    private Long userID;

    private Level level;

    private Environment environment;

    private Timestamp archived;

    @CreatedDate
    private Timestamp createAt;

    public Error() {
    }
}
