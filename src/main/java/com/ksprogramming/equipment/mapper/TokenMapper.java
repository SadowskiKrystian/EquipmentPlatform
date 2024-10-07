package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.data.TokenData;
import com.ksprogramming.equipment.entities.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenMapper {

    public static Token dataToEntity(TokenData tokenData) {
        return Token.builder()
                .id(tokenData.getId())
                .user(UserMapper.dataToEntity(tokenData.getUserData()))
                .value(tokenData.getValue())
                .expirationDate(tokenData.getExpirationDatetime())
                .isUsed(tokenData.getUsed())
                .build();
    }

    public static TokenData entityToData(Token token) {
        return TokenData.builder()
                .id(token.getId())
                .userData(UserMapper.entityToData(token.getUser()))
                .value(token.getValue())
                .expirationDatetime(token.getExpirationDate())
                .isUsed(token.getUsed())
                .build();
    }

    public static List<Token> dataToEntityList(List<TokenData> tokenDataList) {
        List<Token> tokens = new ArrayList<>();
        tokenDataList.forEach(tokenData -> {tokens.add(dataToEntity(tokenData));});
        return tokens;
    }

    public static List<TokenData> entityListToDataList(List<Token> tokens) {
        List<TokenData> tokenDataList = new ArrayList<>();
        tokens.forEach(token -> {tokenDataList.add(entityToData(token));});
        return tokenDataList;
    }

}
