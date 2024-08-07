package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.UserAuthorityData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.enumes.Authority;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.entities.UserAuthority;
import com.ksprogramming.equipment.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return userEntityToData(userRepository.findByLogin(email));
    }

    public UserData registerUser(UserData userData){
        UserData user = userEntityToData(userRepository.save(new User(
                userData.getLogin(),
                userData.getPasswordHash(),
                userData.getEmailConfirmed(),
                userData.getLanguage(),
                userData.getRegistrationDate())));
                userAuthorityService.save(new UserAuthorityData(user, Authority.USER.getCodeWithRole()));

        return user;
    }
    public List<UserData> findAll(){
        return usersEntityToData(userRepository.findDidntRemoveUser());
    }

    public UserData getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserData> userData = usersEntityToData(userRepository.findUserByLogin(authentication.getName()));
        if (!userData.isEmpty()){
            return userData.getFirst();
        }else {
            return null;
        }
    }

    public UserData getUserByLogin(String login){
        return userEntityToData(userRepository.findByLogin(login));

    }
    public Boolean isLoggedAdmin() {
        return userAuthorityService.hasCustomerAuthority(getLoggedUser(), Authority.ADMIN);
    }



    public UserData getUserById(Long id){
        return userEntityToData(userRepository.getReferenceById(id.intValue()));

    }
    public void changePasswordAdmin(Long id, String newPassword){
        UserData user = getUserById(id);
        changePassword(user, newPassword);

    }
    public void changeForgottenPassword(UserData user){
        userRepository.save(userDataToEntity(user));
    }

    public void update(UserData userData){
        UserData user = userEntityToData(userRepository.getReferenceById(userData.getId().intValue()));
        user.setLogin(userData.getLogin());
        user.setEmailConfirmed(userData.getEmailConfirmed());
        user.setLanguage(userData.getLanguage());
        User originalUser = userDataToEntity(user);
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
        UserData user = userEntityToData(userRepository.getReferenceById(id.intValue()));
        user.setDeleteDate(LocalDateTime.now());
        userRepository.save(userDataToEntity(user));
        userAuthorityService.delete(id);

    }

    private void changePassword(UserData user, String newPassword) {
        user.setPasswordHash(newPassword);
        userRepository.save(userDataToEntity(user));
    }
    private List<UserAuthorityData> userAuthoritiesEntityToData(List<UserAuthority> userAuthorities){
        List<UserAuthorityData> list = new ArrayList<>();
        userAuthorities.stream()
                .forEach(authority -> {
                    list.add(new UserAuthorityData(authority.getAuthority()));
                });
        return list;
    }
    private UserAuthorityData userAuthorityEntityToData(UserAuthority userAuthorityEntity){
        return new UserAuthorityData(userAuthorityEntity.getId(), userEntityToData(userAuthorityEntity.getUser()),
                userAuthorityEntity.getAuthority());
    }
    private User userDataToEntity(UserData userData){
        return new User(userData.getId(), userData.getLogin(),
                userData.getPasswordHash(), userData.getEmailConfirmed(),
                userData.getLanguage(),
                userData.getRegistrationDate(),
                userData.getDeleteDate());
    }


    private UserData userEntityToData(User login) {
        return  new UserData(login.getId(), login.getLogin(), login.getPasswordHash(),
                login.getEmailConfirmed(), login.getLanguage(),userAuthoritiesEntityToData(login.getUserAuthorities()), login.getRegistrationDate());


    }
    private List<UserData> usersEntityToData(List<User> users){
        List<UserData> list = new ArrayList<>();
        users.stream()
                .forEach(user -> {
                    list.add(new UserData(user.getId(), user.getLogin(), user.getPasswordHash(),
                            user.getEmailConfirmed(), user.getLanguage(), user.getRegistrationDate()));
                });
        return  list;
    }
    private List<UserAuthority> userAuthoritiesDataToEntity(List<UserAuthorityData> userAuthoritiesData){
        List<UserAuthority> list = new ArrayList<>();
        userAuthoritiesData.stream()
                .forEach(authority -> {
                    list.add(new UserAuthority(authority.getAuthority()));
                });
        return list;
    }


}


