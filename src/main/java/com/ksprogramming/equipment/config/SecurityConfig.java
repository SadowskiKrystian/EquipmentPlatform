package com.ksprogramming.equipment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
       return new MessageDigestPasswordEncoder("SHA-256");
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/admin/**").permitAll()
                        .anyRequest().permitAll()
        );
        http.formLogin(form -> form.loginPage("/login").permitAll());
        http.formLogin(form -> form.defaultSuccessUrl("/"));
        http.logout(logout -> logout.logoutSuccessUrl("/login").permitAll());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
