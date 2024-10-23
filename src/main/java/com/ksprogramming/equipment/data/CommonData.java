package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.api.AttributeGetResponse;
import com.ksprogramming.equipment.api.EquipmentGetResponse;
import com.ksprogramming.equipment.api.EquipmentWithAttributesGetResponse;
import com.ksprogramming.equipment.api.PictureGetResponse;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.mapper.PictureMapper;
import com.ksprogramming.equipment.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class CommonData {

    public static List<UserAuthorityData> userAuthoritiesArrayToList(String[] authorities) {
        List<UserAuthorityData> list = new ArrayList<>();
        for (String authority : authorities) {
            list.add(new UserAuthorityData(Authority.valueOf(authority).getCodeWithRole()));
        }
        return list;
    }

    public static EquipmentWithAttributesGetResponse prepareEquipmentWithAttributesGetResponse(EquipmentData equipmentData, List<AttributeData> assignedAttributesData, List<AttributeData> attributesData) {
        List<AttributeGetResponse> assignedAttributes = new ArrayList<>();
        assignedAttributesData.stream()
                .forEach(attribute -> assignedAttributes.add(new AttributeGetResponse(attribute)));
        List<AttributeGetResponse> attributes = new ArrayList<>();
        attributesData.stream()
                .forEach(attribute -> {
                    attributes.add(new AttributeGetResponse(attribute));
                });
        return new EquipmentWithAttributesGetResponse(new EquipmentGetResponse(equipmentData.getId(), UserMapper.dataToGetResponse(equipmentData.getUserData()),
                equipmentData.getPicture() != null? PictureMapper.DataToGetResponse(equipmentData.getPicture()) : new PictureGetResponse(), equipmentData.getName(), equipmentData.getCreateDate(), equipmentData.getEditDate()),
                assignedAttributes, attributes);

    }
}
