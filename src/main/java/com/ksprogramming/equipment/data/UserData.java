package com.ksprogramming.equipment.data;

import java.time.LocalDateTime;
import java.util.List;

public class UserData {
    private Long id;
    private String login;
    private String passwordHash;
    private Boolean emailConfirmed;
    private String language;
    private List<UserAuthorityData> userAuthoritiesData;
    private LocalDateTime registrationDate;
    private LocalDateTime deleteDate;

    public UserData(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public UserData(Long id, String login, LocalDateTime registrationDate) {
        this.id = id;
        this.login = login;
        this.registrationDate = registrationDate;
    }

    public UserData(Long id, String login, String passwordHash,
                             Boolean emailConfirmed, String language, LocalDateTime registrationDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate;
    }

    public UserData(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, LocalDateTime registrationDate, LocalDateTime deleteDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
    }

    public UserData(Long id, String login, Boolean emailConfirmed, String language, List<UserAuthorityData> userAuthoritiesData) {
        this.id = id;
        this.login = login;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.userAuthoritiesData = userAuthoritiesData;
    }

    public UserData(String login, String passwordHash, Boolean emailConfirmed, String language, List<UserAuthorityData> userAuthoritiesData, LocalDateTime registrationDate) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.userAuthoritiesData = userAuthoritiesData;
        this.registrationDate = registrationDate;
    }

    public UserData(String login, String passwordHash, Boolean emailConfirmed, String language, LocalDateTime registrationDate) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate;
    }

    public UserData(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, List<UserAuthorityData> userAuthoritiesData, LocalDateTime registrationDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.userAuthoritiesData = userAuthoritiesData;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public List<UserAuthorityData> getUserAuthoritiesData() {
        return userAuthoritiesData;
    }

    public void setUserAuthoritiesData(List<UserAuthorityData> userAuthoritiesData) {
        this.userAuthoritiesData = userAuthoritiesData;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

}
