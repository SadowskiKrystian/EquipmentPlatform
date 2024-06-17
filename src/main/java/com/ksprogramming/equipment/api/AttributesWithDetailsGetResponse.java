package com.ksprogramming.equipment.api;

import com.ksprogramming.equipment.logic.equipment.AttributeType;

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
}
