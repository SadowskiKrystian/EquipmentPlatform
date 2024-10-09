package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.UserAuthorityGetResponse;
import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.entities.UserAuthority;
import com.ksprogramming.equipment.enumes.Authority;

import java.util.ArrayList;
import java.util.List;

public class UserAuthorityMapper {

    public static UserAuthority dataToEntity(UserAuthorityData userAuthorityData) {
        return UserAuthority.builder()
                .id(userAuthorityData.getId())
                .user(UserMapper.dataToEntity(userAuthorityData.getUserData()))
                .authority(userAuthorityData.getAuthority())
                .build();
    }

    public static UserAuthorityData entityToData(UserAuthority userAuthority) {
        return UserAuthorityData.builder()
                .id(userAuthority.getId())
                .userData(UserMapper.entityToData(userAuthority.getUser()))
                .authority(userAuthority.getAuthority())
                .build();
    }


    public static List<UserAuthorityData> entityToDataList(List<UserAuthority> userAuthorityList) {
        List<UserAuthorityData> list = new ArrayList<>();
        userAuthorityList.stream()
                .forEach(authority -> {
                    list.add(new UserAuthorityData(authority.getAuthority()));
                });
        return list;
    }

    public static List<UserAuthority> dataToEntityList(List<UserAuthorityData> userAuthorityDataList) {
        List<UserAuthority> userAuthorityList = new ArrayList<>();
        userAuthorityDataList.forEach(userAuthorityData -> {
            userAuthorityList.add(dataToEntity(userAuthorityData));
        });
        return userAuthorityList;
    }

    public static List<UserAuthorityGetResponse> DataToGetResponse(List<UserAuthorityData> userAuthorityDataList) {
        List<UserAuthorityGetResponse> userAuthorityGetResponseList = new ArrayList<>();
        userAuthorityDataList.stream()
                .forEach(authority -> {
                    userAuthorityGetResponseList.add(UserAuthorityGetResponse.builder()
                            .id(authority.getId())
                            .equipmentUserGetResponse(UserMapper.dataToGetResponse(authority.getUserData()))
                            .authority(Authority.findByCodeWithRole(authority.getAuthority()).toString())
                            .build());
                });
        return userAuthorityGetResponseList;
    }


}
