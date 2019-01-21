package com.smirix.pojo;

/**
 * Created by Виктор on 20.01.2019.
 */
public class TelegramChannel {
    private Long id;
    private Long userId;
    private String name;
    private Long userTelegramId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public void setUserTelegramId(Long userTelegramId) {
        this.userTelegramId = userTelegramId;
    }
}
