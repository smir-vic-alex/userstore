package com.smirix.services;

import com.smirix.pojo.TelegramChannel;
import com.smirix.pojo.TelegramUser;
import com.smirix.senders.*;
import com.smirix.senders.requests.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by Виктор on 21.01.2019.
 */
public class TlgmNetworkService {

    private AddTelegramBotSender addTelegramBotSender;
    private AddTelegramChannelSender addTelegramChannelSender;
    private ExecuteTelegramMessageSender executeTelegramMessageSender;
    private GetUserTelegramBotsSender getUserTelegramBotsSender;
    private GetUserTelegramChannelsSender getUserTelegramChannelsSender;

    public void setAddTelegramBotSender(AddTelegramBotSender addTelegramBotSender) {
        this.addTelegramBotSender = addTelegramBotSender;
    }

    public void setAddTelegramChannelSender(AddTelegramChannelSender addTelegramChannelSender) {
        this.addTelegramChannelSender = addTelegramChannelSender;
    }

    public void setExecuteTelegramMessageSender(ExecuteTelegramMessageSender executeTelegramMessageSender) {
        this.executeTelegramMessageSender = executeTelegramMessageSender;
    }

    public void setGetUserTelegramBotsSender(GetUserTelegramBotsSender getUserTelegramBotsSender) {
        this.getUserTelegramBotsSender = getUserTelegramBotsSender;
    }

    public void setGetUserTelegramChannelsSender(GetUserTelegramChannelsSender getUserTelegramChannelsSender) {
        this.getUserTelegramChannelsSender = getUserTelegramChannelsSender;
    }

    public void addBot(String token, String name, Long userId) {

        AddBot addBot = new AddBot();
        addBot.setName(name);
        addBot.setToken(token);
        addBot.setUserId(userId);

        try {
            addTelegramBotSender.send(addBot);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addChannel(Long userId, Long botId, String channelName) {
        AddChannel rq = new AddChannel();

        rq.setUserId(userId);
        rq.setBotId(botId);
        rq.setChannelName(channelName);
        try {
            addTelegramChannelSender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message, String channelName, Long userId) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        executeMessage.setUserId(userId);
        executeMessage.setChannelName(channelName);
        executeMessage.setMessage(message);
        try {
            executeTelegramMessageSender.send(executeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<TelegramUser> getUserBots(Long userId) {

        BotsRq rq = new BotsRq();
        rq.setUserId(userId);

        try {
            return getUserTelegramBotsSender.send(rq).getBots();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<TelegramChannel> getUserChannels(Long userId) {
        ChannelRq rq = new ChannelRq();
        rq.setUserId(userId);
        try {
            return getUserTelegramChannelsSender.send(rq).getChannels();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
