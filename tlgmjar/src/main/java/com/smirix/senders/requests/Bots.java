package com.smirix.senders.requests;

import com.smirix.pojo.TelegramUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class Bots implements Serializable {

    private List<TelegramUser> bots;

    public List<TelegramUser> getBots() {
        return bots;
    }

    public void setBots(List<TelegramUser> bots) {
        this.bots = bots;
    }
}
