package com.ksprogramming.equipment.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserGetResponse {
    private Long id;
    private String login;
    private String passwordHash;
    private Boolean emailConfirmed;
    private String language;
    private String registrationDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserGetResponse(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, LocalDateTime registrationDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate.format(FORMATTER);
    }

    public UserGetResponse(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public String getLanguage() {
        return language;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public static UserGetResponseBuilder builder(){
        return new UserGetResponseBuilder();
    }

    public static class UserGetResponseBuilder {
        private Long id;
        private String login;
        private String passwordHash;
        private Boolean emailConfirmed;
        private String language;
        private LocalDateTime registrationDate;

        public UserGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserGetResponseBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserGetResponseBuilder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserGetResponseBuilder emailConfirmed(Boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
            return this;
        }

        public UserGetResponseBuilder language(String language) {
            this.language = language;
            return this;
        }

        public UserGetResponseBuilder registrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserGetResponse build() {
            return new UserGetResponse(id, login, passwordHash, emailConfirmed, language, registrationDate);
        }
    }

}
