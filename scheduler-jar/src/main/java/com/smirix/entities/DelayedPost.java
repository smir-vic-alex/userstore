package com.smirix.entities;


/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-09
 */
public class DelayedPost {
    private Long id;
    private Long userId;
    private String message;
    private Boolean fromGroup;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(Boolean fromGroup) {
        this.fromGroup = fromGroup;
    }

}