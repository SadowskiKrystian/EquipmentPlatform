package com.ksprogramming.equipment.api;

public class UserAuthorityGetResponse {
    private Long id;
    private EquipmentUserGetResponse equipmentUserGetResponse;
    private String authority;

    public UserAuthorityGetResponse() {
    }

    public UserAuthorityGetResponse(Long id, EquipmentUserGetResponse equipmentUserGetResponse, String authority) {
        this.id = id;
        this.equipmentUserGetResponse = equipmentUserGetResponse;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public EquipmentUserGetResponse getEquipmentUserGetResponse() {
        return equipmentUserGetResponse;
    }

    public String getAuthority() {
        return authority;
    }
}
