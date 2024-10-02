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

    public static UserLoginDataBuilder builder(){
        return new UserLoginDataBuilder();
    }

    public static class UserLoginDataBuilder {
        private String login;
        private String password;
        private List<UserAuthorityData> userAuthorities;

        public UserLoginDataBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserLoginDataBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserLoginDataBuilder userAuthorities(List<UserAuthorityData> userAuthorities) {
            this.userAuthorities = userAuthorities;
            return this;
        }

        public UserLoginData build() {
            return new UserLoginData(login, password, userAuthorities);
        }
    }
}
