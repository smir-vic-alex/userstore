package com.smirix.utils;

import com.smirix.entities.Login;
import com.smirix.entities.Token;

import java.util.Calendar;
import java.util.UUID;

/**
 * Class description
 *
 * @author sbrf-Smirnov-VA
 * @created on 2019-05-23
 */
public class TokenUtils {

    public static Token generateToken(Login login) {
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUserId(login.getUserId());
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.MINUTE, 1);
        token.setExpired(expired);

        return token;
    }
}
