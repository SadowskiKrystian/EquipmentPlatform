package com.ksprogramming.equipment.enumes;

public enum AttributeType {
    STRING ("String"),
    INT ("Int"),
    DATE ("Date"),
    DATETIME ("DateTime"),
    TIME ("Time"),
    DOUBLE ("Double");

    private String name;

    AttributeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
