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
    private String status;
    private String type;
    private Long ownerId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
