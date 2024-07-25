package com.ksprogramming.equipment.api;

public class UserPutRequest {
    private String login;
    private String language;
    private Boolean emailConfirmed;
    private String[] authorities;

    public UserPutRequest() {
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
