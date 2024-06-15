package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.entities.UserAuthority;
import com.ksprogramming.equipment.repository.UserAuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserAuthorityService implements UserAuthorityServiceInterface{
    private UserAuthorityRepository userAuthorityRepository;

    public UserAuthorityService(UserAuthorityRepository userAuthorityRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
    }

    public UserAuthorityData save(UserAuthorityData userAuthorityData){
        UserAuthorityData userAuthority = userAuthorityEntityToData(userAuthorityRepository.save(userAuthorityDataToEntity(userAuthorityData)));
        return userAuthority;
    }
    public void update(UserAuthorityData userAuthorityData){
        userAuthorityEntityToData(userAuthorityRepository.save(userAuthorityDataToEntity(userAuthorityData)));
    }
    public UserAuthorityData get(Long id){
        return userAuthorityEntityToData(userAuthorityRepository.getReferenceById(id.intValue()));
    }

    public List<UserAuthorityData> findAll(){
        List<UserAuthorityData> list = userAuthoritiesEntityToData(userAuthorityRepository.findAll());
        return list;
    }
    public List<UserAuthorityData> findById(Long id){
        List<UserAuthorityData> userAuthorities= userAuthoritiesEntityToData(userAuthorityRepository.findByUserId(id));
        return  userAuthorities;
    }
    public void delete(Long id){
        userAuthorityRepository.deleteByUserId(id);
    }
    private UserAuthority userAuthorityDataToEntity(UserAuthorityData userAuthorityData) {
        return new UserAuthority(userAuthorityData.getId(), equipmentUserDataToEntity(userAuthorityData.getUserData()), userAuthorityData.getAuthority());
    }
    private UserAuthorityData userAuthorityEntityToData(UserAuthority userAuthorityEntity) {
        return new UserAuthorityData(userAuthorityEntity.getId(), equipmentUserEntityToData(userAuthorityEntity.getUser()),
                userAuthorityEntity.getAuthority());
    }

    private User equipmentUserDataToEntity(UserData userData) {
        return new User(userData.getId(), userData.getLogin(), userData.getPasswordHash(),
                userData.getEmailConfirmed(), userData.getLanguage(), userData.getRegistrationDate());
    }

    private List<UserAuthorityData> userAuthoritiesEntityToData(List<UserAuthority> userAuthorityEntities) {
        List<UserAuthorityData> list = new ArrayList<>();
        userAuthorityEntities.stream()
                .forEach(authority -> {
                    list.add(new UserAuthorityData(authority.getId(), equipmentUserEntityToData(authority.getUser()),
                            authority.getAuthority()));
                });
        return list;
    }

    private UserData equipmentUserEntityToData(User user) {
        UserData userData = new UserData(user.getId(), user.getLogin(),
                user.getPasswordHash(), user.getEmailConfirmed(), user.getLanguage(),
                user.getRegistrationDate());
        return userData;
    }

}
