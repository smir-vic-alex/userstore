package com.smirix.senders.requests;

import java.io.Serializable;

/**
 * Created by Виктор on 21.01.2019.
 */
public class AddBot implements Serializable {
    private String token;
    private String name;
    private Long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
