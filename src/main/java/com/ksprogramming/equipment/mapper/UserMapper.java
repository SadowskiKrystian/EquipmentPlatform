package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.UserGetResponse;
import com.ksprogramming.equipment.api.UserPutRequest;
import com.ksprogramming.equipment.data.Common;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User dataToEntity(UserData userData) {
        return User.builder()
                .id(userData.getId())
                .login(userData.getLogin())
                .passwordHash(userData.getPasswordHash())
                .emailConfirmed(userData.getEmailConfirmed())
                .language(userData.getLanguage())
                .userAuthorities(UserAuthorityMapper.dataToEntityList(userData.getUserAuthoritiesData()))
                .equipments(EquipmentMapper.dataToEntityList(userData.getEquipments()))
                .notification(NotificationMapper.dataToEntityList(userData.getNotification()))
                .registrationDate(userData.getRegistrationDate())
                .deleteDate(userData.getDeleteDate())
                .build();
    }

    public static UserData entityToData(User user) {
        return UserData.builder()
                .id(user.getId())
                .login(user.getLogin())
                .passwordHash(user.getPasswordHash())
                .emailConfirmed(user.getEmailConfirmed())
                .language(user.getLanguage())
                .userAuthoritiesData(UserAuthorityMapper.entityToDataList(user.getUserAuthorities()))
                .equipments(EquipmentMapper.entityToDataList(user.getEquipmentsEntity()))
                .notification(NotificationMapper.entityToDataList(user.getNotification()))
                .registrationDate(user.getRegistrationDate())
                .deleteDate(user.getDeleteDate())
                .build();
    }

    public static List<UserData> entityToDataList(List<User> users) {
        List<UserData> userDataList = new ArrayList<>();
        users.forEach(user -> userDataList.add(entityToData(user)));
        return userDataList;
    }

    public static List<User> dataToEntityList(List<UserData> userDataList) {
        List<User> users = new ArrayList<>();
        userDataList.forEach(userData -> users.add(dataToEntity(userData)));
        return users;
    }

    public static UserData putRequestToData(Long id, UserPutRequest equipmentUserPutRequest) {
        UserData user = UserData.builder()
                .id(id)
                .login(equipmentUserPutRequest.getLogin())
                .emailConfirmed(equipmentUserPutRequest.getEmailConfirmed())
                .language(equipmentUserPutRequest.getLanguage())
                .userAuthoritiesData(Common.userAuthoritiesArrayToList(equipmentUserPutRequest.getAuthorities()))
                .build();
        return user;
    }

    public static UserGetResponse dataToGetResponse(UserData user) {
        return UserGetResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .passwordHash(user.getPasswordHash())
                .emailConfirmed(user.getEmailConfirmed())
                .language(user.getLanguage())
                .registrationDate(user.getRegistrationDate())
                .build();
    }
    public static List<UserGetResponse> dataToGetResponseList(List<UserData> equipmentUserData) {
        List<UserGetResponse> list = new ArrayList<>();
        equipmentUserData.stream()
                .forEach(user -> {
                    list.add(UserGetResponse.builder()
                            .id(user.getId())
                            .login(user.getLogin())
                            .passwordHash(user.getPasswordHash())
                            .emailConfirmed(user.getEmailConfirmed())
                            .language(user.getLanguage())
                            .registrationDate(user.getRegistrationDate())
                            .build());
                });
        return list;
    }

}
