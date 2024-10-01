package com.ksprogramming.equipment.entities;

import jakarta.persistence.*;

@Entity
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String authority;

    public UserAuthority() {
    }

    public UserAuthority(String authority) {
        this.authority = authority;
    }

    public UserAuthority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public UserAuthority(Long id, User user, String authority) {
        this.id = id;
        this.user = user;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAuthority() {
        return authority;
    }

    public static UserAuthorityBuilder builder(){
        return new UserAuthorityBuilder();
    }

    public static class UserAuthorityBuilder {
        private Long id;
        private User user;
        private String authority;

        public UserAuthorityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserAuthorityBuilder user(User user) {
            this.user = user;
            return this;
        }

        public UserAuthorityBuilder authority(String authority) {
            this.authority = authority;
            return this;
        }

        public UserAuthority build() {
            return new UserAuthority(id, user, authority);
        }
    }
}
