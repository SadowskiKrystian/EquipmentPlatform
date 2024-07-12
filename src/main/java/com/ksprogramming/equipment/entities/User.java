package com.ksprogramming.equipment.entities;

import jakarta.persistence.*;
import org.springframework.context.annotation.Profile;

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
    @OneToMany(mappedBy = "receiverId")
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

}
