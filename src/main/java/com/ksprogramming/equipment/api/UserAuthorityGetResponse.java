package com.ksprogramming.equipment.api;

public class UserAuthorityGetResponse {
    private Long id;
    private UserGetResponse equipmentUserGetResponse;
    private String authority;

    public UserAuthorityGetResponse() {
    }

    public UserAuthorityGetResponse(Long id, UserGetResponse equipmentUserGetResponse, String authority) {
        this.id = id;
        this.equipmentUserGetResponse = equipmentUserGetResponse;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public UserGetResponse getEquipmentUserGetResponse() {
        return equipmentUserGetResponse;
    }

    public String getAuthority() {
        return authority;
    }
}
