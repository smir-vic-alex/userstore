package com.smirix.senders.requests;

import java.io.Serializable;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ExecuteMessage implements Serializable {
    private Long userId;
    private String channelName;
    private String message;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
