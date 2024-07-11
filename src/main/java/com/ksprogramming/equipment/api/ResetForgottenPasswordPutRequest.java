package com.ksprogramming.equipment.api;

public class ResetForgottenPasswordPutRequest {
    private String passwordHash;
    private String token;

    public ResetForgottenPasswordPutRequest() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getToken() {
        return token;
    }
}
