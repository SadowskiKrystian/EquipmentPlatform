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

    public static TokenBuilder builder(){
        return new TokenBuilder();
    }

    public static class TokenBuilder {
        private Long id;
        private User user;
        private String value;
        private LocalDateTime expirationDate;
        private Boolean isUsed;

        public TokenBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TokenBuilder user(User user) {
            this.user = user;
            return this;
        }

        public TokenBuilder value(String value) {
            this.value = value;
            return this;
        }

        public TokenBuilder expirationDate(LocalDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public TokenBuilder isUsed(Boolean isUsed) {
            this.isUsed = isUsed;
            return this;
        }

        public Token build() {
            return new Token(id, user, value, expirationDate, isUsed);
        }
    }

}
