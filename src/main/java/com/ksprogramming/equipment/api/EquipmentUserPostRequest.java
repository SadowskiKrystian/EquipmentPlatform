package com.ksprogramming.equipment.api;

public class EquipmentUserPostRequest {
    private String login;
    private String passwordHash;
    private Boolean emailConfirmed;
    private String language;
    private String[] authorities;

    public EquipmentUserPostRequest() {
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

    public String[] getAuthorities() {
        return authorities;
    }
}
