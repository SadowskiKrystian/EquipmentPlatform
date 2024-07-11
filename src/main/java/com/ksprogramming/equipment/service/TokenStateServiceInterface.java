package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.TokenData;

public interface TokenStateServiceInterface {
    boolean isTokenExpired(TokenData token);
    boolean isTokenUsed(TokenData token);
}
