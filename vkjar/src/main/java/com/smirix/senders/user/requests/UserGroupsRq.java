package com.smirix.senders.user.requests;

import java.io.Serializable;

/**
 * Class description.
 *
 * @author sbrf-Smirnov-VA
 * @created on 09.01.2019
 */
public class UserGroupsRq implements Serializable {
    private Long userId;
    private Boolean isLinked;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getLinked() {
        return isLinked;
    }

    public void setLinked(Boolean linked) {
        isLinked = linked;
    }
}
