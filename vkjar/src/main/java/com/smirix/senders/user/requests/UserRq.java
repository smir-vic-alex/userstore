package com.smirix.senders.user.requests;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-28
 */
public class UserRq implements Serializable {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
