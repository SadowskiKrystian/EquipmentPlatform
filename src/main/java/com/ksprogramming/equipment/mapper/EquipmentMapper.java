package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.EquipmentGetResponse;
import com.ksprogramming.equipment.api.PictureGetResponse;
import com.ksprogramming.equipment.data.EquipmentData;
import com.ksprogramming.equipment.data.PictureData;
import com.ksprogramming.equipment.entities.Equipment;
import com.ksprogramming.equipment.entities.Picture;

import java.util.ArrayList;
import java.util.List;

public class EquipmentMapper {
    public static Equipment dataToEntity(EquipmentData equipmentData) {
        Picture picture = null;
        if (equipmentData.getPicture() != null) {
            picture = PictureMapper.dataToEntity(equipmentData.getPicture());
        }
        return Equipment.builder()
                .id(equipmentData.getId())
                .user(UserMapper.dataToEntity(equipmentData.getUserData()))
                .picture(picture)
                .name(equipmentData.getName())
                .createDate(equipmentData.getCreateDate())
                .editDate(equipmentData.getEditDate())
                .removeDate(equipmentData.getRemoveDate())
                .build();
    }

    public static EquipmentData entityToData(Equipment equipment) {
        PictureData pictureData = null;
        if (equipment.getPicture() != null) {
            pictureData = PictureMapper.entityToData(equipment.getPicture());
        }

        return EquipmentData.builder()
                .id(equipment.getId())
                .userData(UserMapper.entityToDataWithoutAuthentication(equipment.getUser()))
                .pictureData(pictureData)
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
    public static List<EquipmentGetResponse> dataToGetResponseList(List<EquipmentData> equipmentDataList) {
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
