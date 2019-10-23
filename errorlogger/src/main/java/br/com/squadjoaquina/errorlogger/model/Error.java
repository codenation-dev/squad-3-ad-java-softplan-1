package br.com.squadjoaquina.errorlogger.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Error {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotNull
    private String origin;

    @NotNull
    private String title;

    private String description;

    //TODO: Add association to another class.
    private Long userID;

    //TODO: Add association to another class.
    private Long levelID;

    //TODO: Add association to another class.
    private Long environmentID;


    private Timestamp archived;

    @CreatedDate
    private Timestamp createAt;

    public Error() {
    }
}
