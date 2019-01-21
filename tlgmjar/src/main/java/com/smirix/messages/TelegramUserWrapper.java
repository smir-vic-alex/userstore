package com.smirix.messages;

import com.smirix.pojo.TelegramUser;

import java.util.List;

/**
 * Created by Виктор on 20.01.2019.
 */
public class TelegramUserWrapper {
    private List<TelegramUser> telegramUsers;

    public List<TelegramUser> getTelegramUsers() {
        return telegramUsers;
    }

    public void setTelegramUsers(List<TelegramUser> telegramUsers) {
        this.telegramUsers = telegramUsers;
    }
}
