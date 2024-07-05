package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.TokenData;
import com.ksprogramming.equipment.data.UserData;

public interface TokenServiceInterface {
    TokenData findToken(String string);
    TokenData createToken(UserData userData);
    TokenData updateToken(TokenData tokenData);
}
