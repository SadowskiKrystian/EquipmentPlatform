package com.ksprogramming.equipment.api;

import java.util.List;

public class AttributeWithDetailsGetResponse {
    AttributeGetResponse attribute;
    List<AssignedAttributeGetResponse> assignedAttributes;

    public AttributeWithDetailsGetResponse(AttributeGetResponse attribute, List<AssignedAttributeGetResponse> assignedAttributes) {
        this.attribute = attribute;
        this.assignedAttributes = assignedAttributes;
    }

    public AttributeGetResponse getAttribute() {
        return attribute;
    }

    public List<AssignedAttributeGetResponse> getAssignedAttributes() {
        return assignedAttributes;
    }
}
