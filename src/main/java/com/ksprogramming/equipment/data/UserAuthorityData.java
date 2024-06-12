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

}
