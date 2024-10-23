package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.TokenData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.Token;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.exception.ExpiredorUsedToken;
import com.ksprogramming.equipment.exception.TokenNotFound;
import com.ksprogramming.equipment.mapper.TokenMapper;
import com.ksprogramming.equipment.mapper.UserMapper;
import com.ksprogramming.equipment.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService implements TokenServiceInterface{
    private TokenRepository tokenRepository;
    private TokenStateServiceInterface tokenStateService;

    public TokenService(TokenRepository tokenRepository, TokenStateServiceInterface tokenStateService) {
        this.tokenRepository = tokenRepository;
        this.tokenStateService = tokenStateService;
    }

    public TokenData findToken(String token) {
        TokenData tokenData = verifyTokenExistence(tokenRepository.findByToken(token));
        verifyTokenValidity(tokenData);
        return TokenMapper.entityToData(tokenRepository.findByToken(token));


    }
    public TokenData updateToken(TokenData tokenData) {
         return TokenMapper.entityToData(tokenRepository.save(TokenMapper.dataToEntity(tokenData)));
    }
    public TokenData createToken(UserData userData){
        return TokenMapper.entityToData(tokenRepository.save(new Token(UserMapper.dataToEntity(userData), UUID.randomUUID().toString(),
                LocalDateTime.now().plusHours(1L), false)));
    }

    private void verifyTokenValidity(TokenData tokenData) {
        if (tokenStateService.isTokenExpired(tokenData)) {
            throw new ExpiredorUsedToken("Token has expired ");
        } else if (tokenStateService.isTokenUsed(tokenData)) {
            throw new ExpiredorUsedToken("Token has used ");
        }
    }

    private TokenData verifyTokenExistence(Token token) {
        if (token == null) {
            throw new TokenNotFound("Token not found");
        } else {
            return TokenMapper.entityToData(token);
        }
    }
}
