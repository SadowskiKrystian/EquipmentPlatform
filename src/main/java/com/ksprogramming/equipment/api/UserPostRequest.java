package com.ksprogramming.equipment.api;

import java.util.List;

public class UserPostRequest {
        private String login;
        private String passwordHash;
        private Boolean emailConfirmed;
        private String language;
        private String[] authorities;

        public UserPostRequest() {
        }

        public String getLogin() {
                return login;
        }

        public String getPasswordHash() {
                return passwordHash;
        }

        public Boolean getEmailConfirmed() {
                return emailConfirmed;
        }

        public String getLanguage() {
                return language;
        }

        public String[] getAuthorities() {
                return authorities;
        }
}
