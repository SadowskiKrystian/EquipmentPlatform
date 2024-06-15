package com.ksprogramming.equipment.api;

public class ValuePutRequest {
    private Long id;
    private String value;
    private String attributeName;
    private Long assignedAttributeId;

    public ValuePutRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Long getAssignedAttributeId() {
        return assignedAttributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

}
