package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.mapper.UserMapper;
import com.ksprogramming.equipment.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface{
    private UserRepository userRepository;
    private UserAuthorityServiceInterface userAuthorityService;

    public UserService(UserRepository userRepository, UserAuthorityServiceInterface userAuthorityService) {
        this.userRepository = userRepository;
        this.userAuthorityService = userAuthorityService;
    }
    public UserData getUserByEmail(String email) {
        return UserMapper.entityToData(userRepository.findByLogin(email));
    }

    public UserData registerUser(UserData userData){
        UserData user = UserMapper.entityToData(userRepository.save(User.builder()
                .login(userData.getLogin())
                .passwordHash(userData.getPasswordHash())
                .emailConfirmed(userData.getEmailConfirmed())
                .language(userData.getLanguage())
                .registrationDate(userData.getRegistrationDate())
                .build()
        ));
                userAuthorityService.save(new UserAuthorityData(user, Authority.USER.getCodeWithRole()));

        return user;
    }
    public List<UserData> findAll(){
        return UserMapper.entityToDataList(userRepository.findDidntRemoveUser());
    }

    public UserData getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserData> userData = UserMapper.entityToDataList(userRepository.findUserByLogin(authentication.getName()));
        if (!userData.isEmpty()){
            return userData.getFirst();
        }else {
            return null;
        }
    }

    public UserData getUserByLogin(String login){
        return UserMapper.entityToData(userRepository.findByLogin(login));
    }

    public Boolean isLoggedAdmin() {
        return userAuthorityService.hasCustomerAuthority(getLoggedUser(), Authority.ADMIN);
    }

    public UserData getUserById(Long id){
        return UserMapper.entityToData(userRepository.getReferenceById(id.intValue()));
    }

    public void changePasswordAdmin(Long id, String newPassword){
        UserData user = getUserById(id);
        changePassword(user, newPassword);

    }
    public void changeForgottenPassword(UserData user){
        userRepository.save(UserMapper.dataToEntity(user));
    }

    public void update(UserData userData){
        UserData user = UserMapper.entityToData(userRepository.getReferenceById(userData.getId().intValue()));
        user.setLogin(userData.getLogin());
        user.setEmailConfirmed(userData.getEmailConfirmed());
        user.setLanguage(userData.getLanguage());
        User originalUser = UserMapper.dataToEntity(user);
        userRepository.save(originalUser);
        userAuthorityService.delete(userData.getId());
        if(userData.getUserAuthoritiesData() != null) {
            userData.getUserAuthoritiesData().stream()
                    .forEach(authority -> {
                        userAuthorityService.save(new UserAuthorityData(user, authority.getAuthority()));
                    });
        }

    }
    public void delete(Long id){
        UserData user = UserMapper.entityToData((userRepository.getReferenceById(id.intValue())));
        user.setDeleteDate(LocalDateTime.now());
        userRepository.save(UserMapper.dataToEntity(user));
        userAuthorityService.delete(id);

    }

    private void changePassword(UserData user, String newPassword) {
        user.setPasswordHash(newPassword);
        userRepository.save(UserMapper.dataToEntity(user));
    }
}


