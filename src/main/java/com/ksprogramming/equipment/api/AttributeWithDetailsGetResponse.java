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

    public static AttributeWithDetailsGetResponseBuilder builder(){
        return new AttributeWithDetailsGetResponseBuilder();
    }

    public static class AttributeWithDetailsGetResponseBuilder {
        private AttributeGetResponse attribute;
        private List<AssignedAttributeGetResponse> assignedAttributes;

        public AttributeWithDetailsGetResponseBuilder withAttribute(AttributeGetResponse attribute) {
            this.attribute = attribute;
            return this;
        }

        public AttributeWithDetailsGetResponseBuilder withAssignedAttributes(List<AssignedAttributeGetResponse> assignedAttributes) {
            this.assignedAttributes = assignedAttributes;
            return this;
        }

        public AttributeWithDetailsGetResponse build() {
            return new AttributeWithDetailsGetResponse(attribute, assignedAttributes);
        }
    }
}
