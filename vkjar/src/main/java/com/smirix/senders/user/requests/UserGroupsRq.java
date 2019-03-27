package com.smirix.senders.user.requests;

import java.io.Serializable;

/**
 * Class description.
 *
 * @author Smirnov-VA
 * @created on 09.01.2019
 */
public class UserGroupsRq implements Serializable {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
