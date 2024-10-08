package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.EquipmentGetResponse;
import com.ksprogramming.equipment.api.PictureGetResponse;
import com.ksprogramming.equipment.data.EquipmentData;
import com.ksprogramming.equipment.entities.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentMapper {
    public static Equipment dataToEntity(EquipmentData equipmentData) {
        return Equipment.builder()
                .id(equipmentData.getId())
                .user(UserMapper.dataToEntity(equipmentData.getUserData()))
                .picture(PictureMapper.dataToEntity(equipmentData.getPicture()))
                .name(equipmentData.getName())
                .createDate(equipmentData.getCreateDate())
                .editDate(equipmentData.getEditDate())
                .removeDate(equipmentData.getRemoveDate())
                .build();
    }

    public static EquipmentData entityToData(Equipment equipment) {
        return EquipmentData.builder()
                .id(equipment.getId())
                .userData(UserMapper.entityToData(equipment.getUser()))
                .pictureData(PictureMapper.entityToData(equipment.getPicture()))
                .name(equipment.getName())
                .createDate(equipment.getCreateDate())
                .editDate(equipment.getEditDate())
                .removeDate(equipment.getRemoveDate())
                .build();
    }

    public static List<Equipment> dataToEntityList(List<EquipmentData> equipmentDataList) {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentDataList.forEach(equipmentData -> {
            equipmentList.add(dataToEntity(equipmentData));
        });
        return equipmentList;
    }

    public static List<EquipmentData> entityToDataList(List<Equipment> equipmentList) {
        List<EquipmentData> equipmentDataList = new ArrayList<>();
        equipmentList.forEach(equipmentData -> {
            equipmentDataList.add(entityToData(equipmentData));
        });
        return equipmentDataList;
    }
    public static List<EquipmentGetResponse> DataToGetResponseList(List<EquipmentData> equipmentDataList) {
        List<EquipmentGetResponse> equipments = new ArrayList<>();
        equipmentDataList.stream()
                .forEach(equipment -> equipments.add(new EquipmentGetResponse(equipment.getId()
                        , UserMapper.dataToGetResponse(equipment.getUserData())
                        , equipment.getPicture() != null? PictureMapper.DataToGetResponse(equipment.getPicture()):new PictureGetResponse()
                        , equipment.getName()
                        , equipment.getCreateDate()
                        , equipment.getEditDate())));
        return equipments;
    }
}
