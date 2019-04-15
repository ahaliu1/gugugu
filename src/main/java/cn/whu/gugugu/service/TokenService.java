package cn.whu.gugugu.service;

import cn.whu.gugugu.repository.TokenRepository;
import cn.whu.gugugu.service.impl.TokenImpl;

public class TokenService implements TokenImpl {
    TokenRepository tokenRepository = new TokenRepository();
    @Override
    public String getSession(String code) {
        return tokenRepository.getSession(code);
    }

    @Override
    public String getToken(String session) {
        return tokenRepository.getToken(session);
    }
}
