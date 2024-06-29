package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserData;

import java.util.List;

public interface UserServiceInterface {
    UserData registerUser(UserData equipmentUserData);
    List<UserData> findAll();
    UserData getLoggedUser();
    UserData getUserById(Long id);
    void changePasswordAdmin (Long id, String newPassword);
    void update(UserData equipmentUserData);
    void delete(Long id);
}
