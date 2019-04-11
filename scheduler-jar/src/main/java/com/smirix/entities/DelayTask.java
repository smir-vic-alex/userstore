package com.smirix.entities;

import java.util.Calendar;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-09
 */
public class DelayTask {
    private Long id;
    private Long userId;
    private Calendar fireDate;
    private Long delayPostId;

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

    public Calendar getFireDate() {
        return fireDate;
    }

    public void setFireDate(Calendar fireDate) {
        this.fireDate = fireDate;
    }

    public Long getDelayPostId() {
        return delayPostId;
    }

    public void setDelayPostId(Long delayPostId) {
        this.delayPostId = delayPostId;
    }
}
