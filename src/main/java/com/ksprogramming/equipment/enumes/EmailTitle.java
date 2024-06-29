package com.ksprogramming.equipment.enumes;

public enum EmailTitle {
    AFTER_REGISTRATION("r"),
    AFTER_PASSWORD_CHANGE("p"),
    PASSWORD_RESET("n"),
    EMAIL_CONFIRMATION_LINK("c");

    private String code;

    EmailTitle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
