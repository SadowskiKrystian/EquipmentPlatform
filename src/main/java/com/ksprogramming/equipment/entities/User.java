package com.ksprogramming.equipment.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "email_confirmed")
    private Boolean emailConfirmed;
    private String language;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserAuthority> userAuthorities;
    @OneToMany(mappedBy = "user")
    private List<Equipment> equipments;
    @OneToMany(mappedBy = "receiver")
    private List<Notification> notification;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;



    public User() {
    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(Long id, String login, String passwordHash, String language) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
    }

    public User(String login, String passwordHash, String language) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.language = language;
    }

    public User(Long id, String login, Boolean emailConfirmed, String language) {
        this.id = id;
        this.login = login;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
    }

    public User(Long id, String login, String passwordHash, Boolean emailConfirmed,
                               String language, LocalDateTime registrationDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate;
    }

    public User(String login, String passwordHash, Boolean emailConfirmed, String language, LocalDateTime registrationDate) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate;
    }

    public User(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, LocalDateTime registrationDate, LocalDateTime deleteDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
    }

    public User(Long id, String login, String passwordHash, Boolean emailConfirmed, String language, List<UserAuthority> userAuthorities,
                List<Equipment> equipments, List<Notification> notification, LocalDateTime registrationDate, LocalDateTime deleteDate) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.emailConfirmed = emailConfirmed;
        this.language = language;
        this.userAuthorities = userAuthorities;
        this.equipments = equipments;
        this.notification = notification;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
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

    public List<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public List<Equipment> getEquipmentsEntity() {
        return equipments;
    }

    public List<Notification> getNotification() {
        return notification;
    }


    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String login;
        private String passwordHash;
        private Boolean emailConfirmed;
        private String language;
        private LocalDateTime registrationDate;
        private LocalDateTime deleteDate;
        private List<UserAuthority> userAuthorities;
        private List<Equipment> equipments;
        private List<Notification> notification;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserBuilder emailConfirmed(Boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
            return this;
        }

        public UserBuilder language(String language) {
            this.language = language;
            return this;
        }

        public UserBuilder registrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder deleteDate(LocalDateTime deleteDate) {
            this.deleteDate = deleteDate;
            return this;
        }

        public UserBuilder userAuthorities(List<UserAuthority> userAuthorities) {
            this.userAuthorities = userAuthorities;
            return this;
        }
        public UserBuilder equipments(List<Equipment> equipments) {
            this.equipments = equipments;
            return this;
        }

        public UserBuilder notification(List<Notification> notification) {
            this.notification = notification;
            return this;
        }

        public User build() {
            return new User(id, login, passwordHash, emailConfirmed, language, userAuthorities, equipments, notification, registrationDate, deleteDate);
        }
    }
}
