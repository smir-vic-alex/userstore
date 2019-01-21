package com.smirix.senders.requests;

import java.io.Serializable;

/**
 * Created by Виктор on 21.01.2019.
 */
public class BotsRq implements Serializable {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
