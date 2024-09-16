package com.shop.vtluan.service;

import org.springframework.stereotype.Service;

import com.shop.vtluan.model.Token;
import com.shop.vtluan.model.User;
import com.shop.vtluan.repository.TokenRepository;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveToken(String token, String expiryTime, User user) {
        this.tokenRepository.save(new Token(token, expiryTime, user));
    }

    public Token findTokenByToken(String token) {
        return this.tokenRepository.findByToken(token);
    }

    public void deleteToken(Token tokenFind) {
        this.tokenRepository.delete(tokenFind);
    }

}
