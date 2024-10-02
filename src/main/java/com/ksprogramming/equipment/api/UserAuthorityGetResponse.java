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

    public static UserAuthorityGetResponseBuilder builder(){
        return new UserAuthorityGetResponseBuilder();
    }

    public static class UserAuthorityGetResponseBuilder {
        private Long id;
        private UserGetResponse equipmentUserGetResponse;
        private String authority;

        public UserAuthorityGetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserAuthorityGetResponseBuilder equipmentUserGetResponse(UserGetResponse equipmentUserGetResponse) {
            this.equipmentUserGetResponse = equipmentUserGetResponse;
            return this;
        }

        public UserAuthorityGetResponseBuilder authority(String authority) {
            this.authority = authority;
            return this;
        }

        public UserAuthorityGetResponse build() {
            return new UserAuthorityGetResponse(id, equipmentUserGetResponse, authority);
        }
    }
}
