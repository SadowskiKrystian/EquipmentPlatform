package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.AttributeGetResponse;
import com.ksprogramming.equipment.api.AttributePostRequest;
import com.ksprogramming.equipment.data.AttributeData;
import com.ksprogramming.equipment.entities.Attribute;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AttributeMapper {

    public static  Attribute dataToEntity(AttributeData attributeData) {
        return Attribute.builder()
                .id(attributeData.getId())
                .name(attributeData.getName())
                .type(attributeData.getType())
                .domain(attributeData.getDomain())
                .assignedAttributes(AssignedAttributeMapper.dataToEntityList(attributeData.getAssignedAttributesData()))
                .createDate(attributeData.getCreateDate())
                .editDate(attributeData.getEditDate())
                .removeDate(attributeData.getRemoveDate())
                .build();
    }

    public static AttributeData entityToData(Attribute attribute) {
        return AttributeData.builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .type(attribute.getType())
                .domain(attribute.getDomain())
                .assignedAttributesData(AssignedAttributeMapper.entitToDataList(attribute.getAssignedAttributes()))
                .createDate(attribute.getCreateDate())
                .editDate(attribute.getEditDate())
                .removeDate(attribute.getRemoveDate())
                .build();
    }

    public static List<AttributeData> entityToDataList(List<Attribute> attributes) {
        List<AttributeData> attributeDataList = new ArrayList<>();
        attributes.forEach(attribute -> attributeDataList.add(entityToData(attribute)));
        return attributeDataList;
    }

    public static List<Attribute> dataToEntityList(List<AttributeData> attributeDataList) {
        List<Attribute> attributes = new ArrayList<>();
        attributeDataList.forEach(attributeData -> attributes.add(dataToEntity(attributeData)));
        return attributes;
    }

    public static AttributeData postRequestToData(AttributePostRequest attributePostRequest) {
        return AttributeData.builder()
                .name(attributePostRequest.getName())
                .type(attributePostRequest.getType())
                .domain(attributePostRequest.getDomain())
                .createDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }

    public static List<AttributeGetResponse> dataToGetResponseList(List<AttributeData> attributesData) {
        List<AttributeGetResponse> attributes = new ArrayList<>();
        attributesData.stream()
                .forEach(attribute -> {
                    attributes.add(new AttributeGetResponse(attribute));
                });
        return attributes;
    }

    public static AttributeGetResponse dataToResponse(AttributeData attribute) {
        return new AttributeGetResponse(attribute);
    }
}
