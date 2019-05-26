package com.smirix.utils;

import javax.ws.rs.core.NewCookie;

import static javax.ws.rs.core.Cookie.DEFAULT_VERSION;
import static javax.ws.rs.core.NewCookie.DEFAULT_MAX_AGE;

/**
 * Class description
 *
 * @author sbrf-Smirnov-VA
 * @created on 2019-05-23
 */
public class CookieHelper {

    public static NewCookie getCookie(String token) {
        return new NewCookie("token",
                token,
                "/",
                null,
                DEFAULT_VERSION,
                null,
                DEFAULT_MAX_AGE,
                null,
                false,
                false);
    }
}
