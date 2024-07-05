package com.ksprogramming.equipment.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private String value;
    private LocalDateTime expirationDate;
    @Column(name = "is_used")
    private Boolean isUsed;


    public Token() {
    }

    public Token(Long id, User user, String value, LocalDateTime expirationDatetime, Boolean isUsed) {
        this.id = id;
        this.user = user;
        this.value = value;
        this.isUsed = isUsed;
        this.expirationDate = expirationDatetime;
    }

    public Token(User user, String value, LocalDateTime expirationDatetime, Boolean isUsed) {
        this.user = user;
        this.value = value;
        this.isUsed = isUsed;
        this.expirationDate = expirationDatetime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }
}
