package com.ksprogramming.equipment.api;

import java.util.List;

public class EquipmentWithAttributesGetResponse {
    private EquipmentGetResponse equipment;
    private List<AttributeGetResponse> assignedAttributes;
    private List<AttributeGetResponse> attributes;
    public EquipmentWithAttributesGetResponse(EquipmentGetResponse equipment, List<AttributeGetResponse> assignedAttributes, List<AttributeGetResponse> attributes) {
        this.equipment = equipment;
        this.assignedAttributes = assignedAttributes;
        this.attributes = attributes;
    }

    public EquipmentGetResponse getEquipment() {
        return equipment;
    }

    public List<AttributeGetResponse> getAssignedAttributes() {
        return assignedAttributes;
    }

    public List<AttributeGetResponse> getAttributes() {
        return attributes;
    }
}
