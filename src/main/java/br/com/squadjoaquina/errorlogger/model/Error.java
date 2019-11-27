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

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @CreatedBy
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Environment environment;

    private LocalDateTime archivedAt;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
