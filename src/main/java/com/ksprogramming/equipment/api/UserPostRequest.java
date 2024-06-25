package com.ksprogramming.equipment.api;

import java.util.List;

public class UserPostRequest {
        private String login;
        private String passwordHash;
        private String language;

        public UserPostRequest() {
        }

        public String getLogin() {
                return login;
        }

        public String getPasswordHash() {
                return passwordHash;
        }

        public String getLanguage() {
                return language;
        }

}
