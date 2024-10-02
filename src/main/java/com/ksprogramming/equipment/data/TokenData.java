package com.ksprogramming.equipment.data;

import java.time.LocalDateTime;

public class TokenData {
    private Long id;
    private UserData userData;
    private String value;
    private Boolean isUsed;
    private LocalDateTime expirationDatetime;

    public TokenData(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public TokenData(Long id, UserData userData, String value, Boolean isUsed, LocalDateTime expirationDatetime) {
        this.id = id;
        this.userData = userData;
        this.value = value;
        this.isUsed = isUsed;
        this.expirationDatetime = expirationDatetime;
    }

    public Long getId() {
        return id;
    }

    public UserData getUserData() {
        return userData;
    }

    public String getValue() {
        return value;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public LocalDateTime getExpirationDatetime() {
        return expirationDatetime;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public void setExpirationDatetime(LocalDateTime expirationDatetime) {
        this.expirationDatetime = expirationDatetime;
    }

    public static TokenDataBuilder builder(){
        return new TokenDataBuilder();
    }

    public static class TokenDataBuilder {
        private Long id;
        private UserData userData;
        private String value;
        private Boolean isUsed;
        private LocalDateTime expirationDatetime;

        public TokenDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TokenDataBuilder userData(UserData userData) {
            this.userData = userData;
            return this;
        }

        public TokenDataBuilder value(String value) {
            this.value = value;
            return this;
        }

        public TokenDataBuilder isUsed(Boolean isUsed) {
            this.isUsed = isUsed;
            return this;
        }

        public TokenDataBuilder expirationDatetime(LocalDateTime expirationDatetime) {
            this.expirationDatetime = expirationDatetime;
            return this;
        }

        public TokenData build() {
            return new TokenData(id, userData, value, isUsed, expirationDatetime);
        }
    }
}
