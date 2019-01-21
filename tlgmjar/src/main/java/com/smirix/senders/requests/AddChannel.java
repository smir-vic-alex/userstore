package com.smirix.senders.requests;

import java.io.Serializable;

/**
 * Created by Виктор on 21.01.2019.
 */
public class AddChannel implements Serializable {
    private String channelName;
    private Long userId;
    private Long botId;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }
}
