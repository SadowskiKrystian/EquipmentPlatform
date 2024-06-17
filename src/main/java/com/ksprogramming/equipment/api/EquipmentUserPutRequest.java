package com.ksprogramming.equipment.api;

public class EquipmentUserPutRequest {
    private String login;
    private String language;
    private Boolean emailConfirmed;
    private String[] authorities;

    public EquipmentUserPutRequest() {
    }

    public String getLogin() {
        return login;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
