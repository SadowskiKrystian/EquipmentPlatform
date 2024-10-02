package com.ksprogramming.equipment.data;

public class UserAuthorityData {
    private Long id;
    private UserData userData;
    private String authority;

    public UserAuthorityData() {
    }

    public UserAuthorityData(Long id, UserData userData, String authority) {
        this.id = id;
        this.userData = userData;
        this.authority = authority;
    }

    public UserAuthorityData(UserData userData, String authority) {
        this.userData = userData;
        this.authority = authority;
    }

    public UserAuthorityData(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public UserData getUserData() {
        return userData;
    }

    public String getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public static UserAuthorityDataBuilder builder(){
        return new UserAuthorityDataBuilder();
    }

    public static class UserAuthorityDataBuilder {
        private Long id;
        private UserData userData;
        private String authority;

        public UserAuthorityDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserAuthorityDataBuilder userData(UserData userData) {
            this.userData = userData;
            return this;
        }

        public UserAuthorityDataBuilder authority(String authority) {
            this.authority = authority;
            return this;
        }

        public UserAuthorityData build() {
            return new UserAuthorityData(id, userData, authority);
        }
    }

}
