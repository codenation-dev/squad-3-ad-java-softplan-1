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

@Entity
public class Error {


    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Getter
    @Setter
    @NotNull
    private String origin;


    @Getter
    @Setter
    @NotNull
    private String title;


    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    //TODO: Add association to another class.
    private Long userID;

    @Getter
    @Setter
    //TODO: Add association to another class.
    private Long levelID;

    @Getter
    @Setter
    //TODO: Add association to another class.
    private Long environmentID;

    @Getter
    @Setter
    private Timestamp archived;

    @CreatedDate
    private Timestamp createAt;

    public Error() {
    }
}