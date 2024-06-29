package com.ksprogramming.equipment.data;

import java.util.List;

public class UserLoginData {
    private String login;
    private String password;
    private List<UserAuthorityData> userAuthorities;

    public UserLoginData(String login, String password, List<UserAuthorityData> userAuthorities) {
        this.login = login;
        this.password = password;
        this.userAuthorities = userAuthorities;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<UserAuthorityData> getUserAuthorities() {
        return userAuthorities;
    }
}
