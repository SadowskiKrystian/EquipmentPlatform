package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.data.PictureData;
import com.ksprogramming.equipment.entities.Picture;

import java.util.ArrayList;
import java.util.List;

public class PictureMapper {
   public static Picture dataToEntity(PictureData pictureData) {
       return Picture.builder()
               .id(pictureData.getId())
               .name(pictureData.getPath())
               .createDate(pictureData.getCreateDate())
               .updateDate(pictureData.getUpdateDate())
               .deleteDate(pictureData.getDeleteDate())
               .build();
   }

   public static PictureData entityToData(Picture picture) {
       return PictureData.builder()
               .id(picture.getId())
               .path(picture.getName())
               .createDate(picture.getCreateDate())
               .updateDate(picture.getUpdateDate())
               .deleteDate(picture.getDeleteDate())
               .build();
   }

   public static List<Picture> dataToEntityList(List<PictureData> pictureDataList) {
       List<Picture> pictureList = new ArrayList<>();
       pictureDataList.forEach(pictureData -> {pictureList.add(dataToEntity(pictureData));});
       return pictureList;
   }

   public static List<PictureData> entityListToDataList(List<Picture> pictureList) {
       List<PictureData> pictureDataList = new ArrayList<>();
       pictureList.forEach(picture -> {pictureDataList.add(entityToData(picture));});
       return pictureDataList;
   }
}
