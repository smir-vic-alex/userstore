package com.smirix.entities;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-28
 */
public class VKUser implements Serializable {
    private Long id;
    private Long userId;
    private Integer vkUserId;

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

    public Integer getVkUserId() {
        return vkUserId;
    }

    public void setVkUserId(Integer vkUserId) {
        this.vkUserId = vkUserId;
    }
}
