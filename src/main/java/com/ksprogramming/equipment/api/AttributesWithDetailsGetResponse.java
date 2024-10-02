package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.enumes.AttributeType;

import java.util.List;

public class AttributesWithDetailsGetResponse {
    List<AttributeGetResponse> attributes;
    AttributeType[] dictionary;

    public AttributesWithDetailsGetResponse(List<AttributeGetResponse> attributes, AttributeType[] dictionary) {
        this.attributes = attributes;
        this.dictionary = dictionary;
    }

    public List<AttributeGetResponse> getAttributes() {
        return attributes;
    }

    public AttributeType[] getDictionary() {
        return dictionary;
    }

    public static AttributesWithDetailsGetResponseBuilder builder(){
        return new AttributesWithDetailsGetResponseBuilder();
    }

    public static class AttributesWithDetailsGetResponseBuilder {
        private List<AttributeGetResponse> attributes;
        private AttributeType[] dictionary;

        public AttributesWithDetailsGetResponseBuilder withAttributes(List<AttributeGetResponse> attributes) {
            this.attributes = attributes;
            return this;
        }

        public AttributesWithDetailsGetResponseBuilder withDictionary(AttributeType[] dictionary) {
            this.dictionary = dictionary;
            return this;
        }

        public AttributesWithDetailsGetResponse build() {
            return new AttributesWithDetailsGetResponse(attributes, dictionary);
        }
    }
}
