package com.ksprogramming.equipment.config;

import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserService userService;

    public UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return createUserDetails(userService.getUserByLogin(username));
    }

    private UserDetails createUserDetails(UserData userByLogin) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        userByLogin.getUserAuthoritiesData().stream()
                .forEach(user-> grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthority())));
        return User.builder()
                .username(userByLogin.getLogin())
                .password(userByLogin.getPasswordHash())
                .authorities(grantedAuthorities)
                .disabled(!userByLogin.getEmailConfirmed())
                .build();
    }
}
