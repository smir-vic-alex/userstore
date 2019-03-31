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
    private Boolean isFromVK;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getFromVK() {
        return isFromVK;
    }

    public void setFromVK(Boolean fromVK) {
        isFromVK = fromVK;
    }
}
