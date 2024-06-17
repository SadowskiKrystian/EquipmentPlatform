package com.ksprogramming.equipment.api;

public class ValuePostRequest {
    private Long id;
    private String attributeName;
    private String value;

    public ValuePostRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
