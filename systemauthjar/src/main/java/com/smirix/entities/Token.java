package com.smirix.entities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Виктор on 17.11.2018.
 */
public class Token {
    private Long id;
    private Long userId;
    private String token;
    private Calendar expired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Calendar getExpired() {
        return expired;
    }

    public void setExpired(Calendar expired) {
        this.expired = expired;
    }
}
