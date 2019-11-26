package br.com.squadjoaquina.errorlogger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    private String origin;

    @NotNull
    @Size(min = 1)
    private String title;

    private String description;

//    @NotNull
//    @CreatedBy
//    private Long userID;

    @CreatedBy
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @CreatedDate
    private LocalDateTime archivedAt;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
