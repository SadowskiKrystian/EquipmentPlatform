package com.ksprogramming.equipment.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EquipmentUserGetResponse {
    private Long id;
    private String login;
    private String passwordHash;
    private Boolean emailConfirmed;
    private String language;
    private String registrationDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EquipmentUserGetResponse(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, LocalDateTime registrationDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate.format(FORMATTER);
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
}
