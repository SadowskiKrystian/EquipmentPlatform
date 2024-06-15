package com.ksprogramming.equipment.enumes;

public enum DomainType {
    EQUIPMENT("Equipment");
    private String code;

    DomainType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
