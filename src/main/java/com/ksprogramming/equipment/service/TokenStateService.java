package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.TokenData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenStateService implements  TokenStateServiceInterface{

    public boolean isTokenExpired(TokenData token) {
        return LocalDateTime.now().isAfter(token.getExpirationDatetime());
    }

    public boolean isTokenUsed(TokenData token) {
        return token.getUsed();
    }
}
