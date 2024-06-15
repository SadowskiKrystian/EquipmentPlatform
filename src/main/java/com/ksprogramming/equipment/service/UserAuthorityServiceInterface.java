package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserAuthorityData;

import java.util.List;

public interface UserAuthorityServiceInterface {
    UserAuthorityData save(UserAuthorityData userAuthorityData);
    void update(UserAuthorityData userAuthorityData);
    UserAuthorityData get(Long id);
    List<UserAuthorityData> findAll();
    List<UserAuthorityData> findById(Long id);
    void delete(Long id);
}
