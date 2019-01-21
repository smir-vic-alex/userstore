package com.smirix.senders.requests;

import com.smirix.pojo.TelegramChannel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class Channels implements Serializable {

    private List<TelegramChannel> channels;

    public List<TelegramChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TelegramChannel> channels) {
        this.channels = channels;
    }
}
