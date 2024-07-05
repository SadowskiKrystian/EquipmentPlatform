package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.TokenData;
import com.ksprogramming.equipment.data.UserData;
import com.ksprogramming.equipment.entities.Token;
import com.ksprogramming.equipment.entities.User;
import com.ksprogramming.equipment.exception.ExpiredorUsedToken;
import com.ksprogramming.equipment.exception.TokenNotFound;
import com.ksprogramming.equipment.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService implements TokenServiceInterface{
    private TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    
    public TokenData findToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null) {
            TokenData tokenData = tokenEntityToData(tokenEntity);
            if (LocalDateTime.now().isAfter(tokenData.getExpirationDatetime()) || tokenData.getUsed() == true) {
                throw new ExpiredorUsedToken("Token has expired or used");
            }
            return tokenEntityToData(tokenRepository.findByToken(token));
        }else {
            throw new TokenNotFound("Token not found");
        }
    }
    public TokenData updateToken(TokenData tokenData) {
         return tokenEntityToData(tokenRepository.save(tokenDataToEntity(tokenData)));
    }



    public TokenData createToken(UserData userData){
        return tokenEntityToData(tokenRepository.save(new Token(userDataToEntity(userData), UUID.randomUUID().toString(), LocalDateTime.now().plusHours(1L), false)));
    }
    private Token tokenDataToEntity(TokenData tokenData) {
        return new Token(tokenData.getId(), userDataToEntity(tokenData.getUserData()), tokenData.getValue(), tokenData.getExpirationDatetime(), tokenData.getUsed());
    }

    private User userDataToEntity(UserData userData) {
        return new User(userData.getId(), userData.getLogin());
    }

    private TokenData tokenEntityToData(Token byToken) {
        return new TokenData(byToken.getId(), userEntityToData(byToken.getUser()), byToken.getValue(), byToken.getUsed(), byToken.getExpirationDate());
    }

    private UserData userEntityToData(User user) {
        return new UserData(user.getId(), user.getLogin());
    }

}
