package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.AssignedAttributeGetResponse;
import com.ksprogramming.equipment.data.AssignedAttributeData;
import com.ksprogramming.equipment.entities.AssignedAttribute;

import java.util.ArrayList;
import java.util.List;

public class AssignedAttributeMapper {

    public static AssignedAttributeData entityToData(AssignedAttribute assignedAttribute) {
        return AssignedAttributeData.builder()
                .id(assignedAttribute.getId())
                .domain(assignedAttribute.getDomain())
                .domainId(assignedAttribute.getDomainId())
                .attributeData(AttributeMapper.entityToData(assignedAttribute.getAttribute()))
                .value(assignedAttribute.getValue())
                .createDate(assignedAttribute.getCreateDate())
                .editDate(assignedAttribute.getEditDate())
                .removeDate(assignedAttribute.getRemoveDate())
                .build();
    }

    public static AssignedAttribute dataToEntity(AssignedAttributeData assignedAttributeData) {
        return AssignedAttribute.builder()
                .id(assignedAttributeData.getId())
                .domain(assignedAttributeData.getDomain())
                .domainId(assignedAttributeData.getDomainId())
                .attribute(AttributeMapper.dataToEntity(assignedAttributeData.getAttribute()))
                .value(assignedAttributeData.getValue())
                .createDate(assignedAttributeData.getCreateDate())
                .editDate(assignedAttributeData.getEditDate())
                .removeDate(assignedAttributeData.getRemoveDate())
                .build();
    }

    public static List<AssignedAttribute> dataToEntityList(List<AssignedAttributeData> assignedAttributeDataList) {
        List<AssignedAttribute> assignedAttributeList = new ArrayList<>();
        assignedAttributeDataList.forEach(assignedAttributeData -> {
            assignedAttributeList.add(dataToEntity(assignedAttributeData));
        });
        return assignedAttributeList;
    }

    public static List<AssignedAttributeData> entitToDataList(List<AssignedAttribute> assignedAttributeList) {
        List<AssignedAttributeData> assignedAttributeDataList = new ArrayList<>();
        assignedAttributeList.forEach(assignedAttributeData -> {
            assignedAttributeDataList.add(entityToData(assignedAttributeData));
        });
        return assignedAttributeDataList;
    }

    public static List<AssignedAttributeGetResponse> dataToGetResponseList(List<AssignedAttributeData> assignedAttributesData) {
        List<AssignedAttributeGetResponse> list = new ArrayList<>();
        assignedAttributesData.stream()
                .forEach(assignedAttributeData -> {
                    list.add(new AssignedAttributeGetResponse(assignedAttributeData));
                });
        return list;
    }
}
