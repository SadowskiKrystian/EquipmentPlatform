package com.ksprogramming.equipment.mapper;

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

}
