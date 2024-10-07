package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.entities.UserAuthority;

import java.util.ArrayList;
import java.util.List;

public class UserAuthorityMapper {
    private Long id;
    private User user;
    private String authority;

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
        List<UserAuthorityData> userAuthorityDataList = new ArrayList<>();
        userAuthorityList.forEach(userAuthority -> {userAuthorityDataList.add(entityToData(userAuthority));});
        return userAuthorityDataList;
    }

    public static List<UserAuthority> dataToEntityList(List<UserAuthorityData> userAuthorityDataList) {
        List<UserAuthority> userAuthorityList = new ArrayList<>();
        userAuthorityDataList.forEach(userAuthorityData -> {userAuthorityList.add(dataToEntity(userAuthorityData));});
        return userAuthorityList;
    }
}
