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

    public static EquipmentWithAttributesGetResponseBuilder builder(){
        return new EquipmentWithAttributesGetResponseBuilder();
    }

    public static class EquipmentWithAttributesGetResponseBuilder {
        private EquipmentGetResponse equipment;
        private List<AttributeGetResponse> assignedAttributes;
        private List<AttributeGetResponse> attributes;

        public EquipmentWithAttributesGetResponseBuilder withEquipment(EquipmentGetResponse equipment) {
            this.equipment = equipment;
            return this;
        }

        public EquipmentWithAttributesGetResponseBuilder withAssignedAttributes(List<AttributeGetResponse> assignedAttributes) {
            this.assignedAttributes = assignedAttributes;
            return this;
        }

        public EquipmentWithAttributesGetResponseBuilder withAttributes(List<AttributeGetResponse> attributes) {
            this.attributes = attributes;
            return this;
        }

        public EquipmentWithAttributesGetResponse build() {
            return new EquipmentWithAttributesGetResponse(equipment, assignedAttributes, attributes);
        }
    }
}
