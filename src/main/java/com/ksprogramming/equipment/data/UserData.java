package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.entities.Equipment;
import com.ksprogramming.equipment.entities.Notification;

import java.time.LocalDateTime;
import java.util.List;

public class UserData {
    private Long id;
    private String login;
    private String passwordHash;
    private Boolean emailConfirmed;
    private String language;
    private List<UserAuthorityData> userAuthoritiesData;
    private List<EquipmentData> equipments;
    private List<NotificationData> notification;
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

    public UserData(Long id, String login, String passwordHash, Boolean emailConfirmed, String language,
                    List<UserAuthorityData> userAuthoritiesData, LocalDateTime registrationDate, LocalDateTime deleteDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.userAuthoritiesData = userAuthoritiesData;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
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

    public UserData(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, List<UserAuthorityData> userAuthoritiesData,
                    List<EquipmentData> equipments, List<NotificationData> notification, LocalDateTime registrationDate, LocalDateTime deleteDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.userAuthoritiesData = userAuthoritiesData;
        this.equipments = equipments;
        this.notification = notification;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
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

    public List<EquipmentData> getEquipments() {
        return equipments;
    }

    public List<NotificationData> getNotification() {
        return notification;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public static UserDataBuilder builder(){
        return new UserDataBuilder();
    }

    public static class UserDataBuilder {
        private Long id;
        private String login;
        private String passwordHash;
        private Boolean emailConfirmed;
        private String language;
        private List<UserAuthorityData> userAuthoritiesData;
        private List<EquipmentData> equipments;
        private List<NotificationData> notification;
        private LocalDateTime registrationDate;
        private LocalDateTime deleteDate;

        public UserDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDataBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserDataBuilder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserDataBuilder emailConfirmed(Boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
            return this;
        }

        public UserDataBuilder language(String language) {
            this.language = language;
            return this;
        }

        public UserDataBuilder userAuthoritiesData(List<UserAuthorityData> userAuthoritiesData) {
            this.userAuthoritiesData = userAuthoritiesData;
            return this;
        }

        public UserDataBuilder equipments(List<EquipmentData> equipments) {
            this.equipments = equipments;
            return this;
        }

        public UserDataBuilder notification(List<NotificationData> notification) {
            this.notification = notification;
            return this;
        }

        public UserDataBuilder registrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserDataBuilder deleteDate(LocalDateTime deleteDate) {
            this.deleteDate = deleteDate;
            return this;
        }

        public UserData build() {
            return new UserData(id, login, passwordHash, emailConfirmed, language, userAuthoritiesData, equipments, notification, registrationDate, deleteDate);
        }
    }
}
