package br.com.aceleradev.javasquad3.errorlogger.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


public class Error {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private Date createad;
    @Column
    private String origin;
    @Column
    private String title;
    @Column
    private String description;
    @Column(name = "user_id")
    //TODO: Add association to another class.
    private Long userID;
    @Column(name = "level_id")
    //TODO: Add association to another class.
    private Long levelID;
    @Column(name = "environment_id")
    private Long environmentID;
    @Column
    private Date archived;


}
