package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.UserAuthority;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.mapper.UserAuthorityMapper;
import com.ksprogramming.equipment.repository.UserAuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAuthorityService implements UserAuthorityServiceInterface{
    private UserAuthorityRepository userAuthorityRepository;

    public UserAuthorityService(UserAuthorityRepository userAuthorityRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
    }

    public UserAuthorityData save(UserAuthorityData userAuthorityData){
        UserAuthorityData userAuthority = UserAuthorityMapper.entityToData(userAuthorityRepository.save(UserAuthorityMapper.dataToEntity(userAuthorityData)));
        return userAuthority;
    }
    public void update(UserAuthorityData userAuthorityData){
        UserAuthorityMapper.entityToData(userAuthorityRepository.save(UserAuthorityMapper.dataToEntity(userAuthorityData)));
    }
    public UserAuthorityData get(Long id){
        return UserAuthorityMapper.entityToData(userAuthorityRepository.getReferenceById(id.intValue()));
    }

    public List<UserAuthorityData> findAll(){
        List<UserAuthorityData> list = UserAuthorityMapper.entityToDataList(userAuthorityRepository.findAll());
        return list;
    }
    public List<UserAuthorityData> findById(Long id){
        List<UserAuthorityData> userAuthorities= UserAuthorityMapper.entityToDataList(userAuthorityRepository.findByUserId(id));
        return  userAuthorities;
    }
    public void delete(Long id){
        userAuthorityRepository.deleteByUserId(id);
    }
    public Boolean hasCustomerAuthority(UserData user, Authority authority) {
        Optional<UserAuthority> userAuthority = userAuthorityRepository.findByIdAndAuthority(user.getId(), authority.getCodeWithRole());
        if(userAuthority.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
