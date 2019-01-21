package com.smirix;

import com.smirix.pojo.TelegramChannel;
import com.smirix.pojo.TelegramUser;
import com.smirix.services.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Инициализатор бота
 * Created by Виктор on 08.03.2018.
 */
public class BotService {

    private static final String BOT_DID_NOT_REGISTER = "Some bot did not register";
    private static final String MESSAGE_DID_NOT_SEND = "Some bot did not send message";
    private static Logger LOGGER = LoggerFactory.getLogger(BotService.class);

    @Autowired
    private TelegramBotsApi botsApi;
    @Autowired
    private TelegramBotFactory botFactory;
    @Autowired
    private TelegramService telegramService;

    private static Map<Integer, TelegramLongPollingBot> registeredBots = new HashMap<>();
    private static Map<String, TelegramLongPollingBot> channelBots = new HashMap<>();

    private synchronized void register(TelegramLongPollingBot bot) {
        try {
            botsApi.registerBot(bot);
        } catch (Exception e) {
            LOGGER.error(BOT_DID_NOT_REGISTER, e);
            throw new TelegramApiModuleException(BOT_DID_NOT_REGISTER, e);
        }
    }

    /**
     * Зарегистрировать нового телеграм бота
     *
     * @param token токен выданный @BotFather
     * @param name имя бота
     */
    public void register(Long userId, String token, String name) {
        TelegramBot bot = getTelegramBot(token, name);
        register(bot);
        User user = getBotInfo(bot);
        TelegramUser telegramUser = getTelegramUser(userId, token, name, user);
        telegramService.saveOrUpdate(telegramUser, TelegramUser.class);

        registeredBots.put(telegramUser.getIdTlgm(), bot);
    }

    public void registerChannel(Long userId, String channelName, Long userTelegramId) {
        TelegramChannel channel = new TelegramChannel();
        channel.setUserId(userId);
        channel.setName(channelName);
        channel.setUserTelegramId(userTelegramId);

        telegramService.saveOrUpdate(channel, TelegramChannel.class);

        TelegramLongPollingBot bot = registeredBots.get(userTelegramId);
        if (bot != null) {
            channelBots.put(channelName, bot);
        }
    }

    private TelegramUser getTelegramUser(Long userId, String token, String name, User user) {
        TelegramUser telegramUser = new TelegramUser();

        telegramUser.setUserId(userId);
        telegramUser.setIsBot(true);
        telegramUser.setFirstName(user.getFirstName());
        telegramUser.setIdTlgm(user.getId());
        telegramUser.setLanguageCode(user.getLanguageCode());
        telegramUser.setLastName(user.getLastName());
        telegramUser.setUserName(user.getUserName());
        telegramUser.setToken(token);
        telegramUser.setName(name);

        return telegramUser;
    }

    private TelegramUser getStubTelegramUser(Long userId, String token, String name, User user) {
        TelegramUser telegramUser = new TelegramUser();

        telegramUser.setUserId(userId);
        telegramUser.setIsBot(true);
        telegramUser.setFirstName("stub");
        telegramUser.setIdTlgm(1);
        telegramUser.setLanguageCode("stub");
        telegramUser.setLastName("stub");
        telegramUser.setUserName("stub");
        telegramUser.setToken("stub");
        telegramUser.setName("stub");

        return telegramUser;
    }

    private User getBotInfo(TelegramLongPollingBot bot) {
        try {
            return bot.execute(new GetMe());
        } catch (TelegramApiException e) {
            LOGGER.error(MESSAGE_DID_NOT_SEND, e);
            throw new TelegramApiModuleException(MESSAGE_DID_NOT_SEND, e);
        }
    }

    private void sendMessage(TelegramLongPollingBot bot, SendMessage message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error(MESSAGE_DID_NOT_SEND, e);
            throw new TelegramApiModuleException(MESSAGE_DID_NOT_SEND, e);
        }
    }

    public void sendMessage(Long userId, String message, String channelName) {

        TelegramLongPollingBot bot = channelBots.get(channelName);

        if (bot == null) {
            List<TelegramChannel> channels = telegramService.getTelegramChannelByTelegramUserId(userId);
            for (TelegramChannel channel : channels) {
                if (channel.getName().equals(channelName)) {
                    List<TelegramUser> telegramUsers = telegramService.getTelegramUserByUserId(userId);

                    for (TelegramUser telegramUser : telegramUsers) {
                        if (telegramUser.getUserId().equals(userId) && telegramUser.getIdTlgm().longValue() == channel.getUserTelegramId()) {
                            register(userId, telegramUser.getToken(), telegramUser.getName());
                            channelBots.put(channelName, registeredBots.get(telegramUser.getIdTlgm()));
                            bot = channelBots.get(channelName);
                            break;
                        }
                    }
                }
            }
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(channelName);

        sendMessage(bot, sendMessage);
    }

    public List<TelegramUser> getUserBots(Long userId) {
        return telegramService.getTelegramUserByUserId(userId);
    }

    public List<TelegramChannel> getUserChannelsByUserId(Long userId) {
        return telegramService.getTelegramChannelByUserId(userId);
    }

    public List<TelegramChannel> getUserChannelsByUserTelegramId(Long userTelegramId) {
        return telegramService.getTelegramChannelByTelegramUserId(userTelegramId);
    }

    private TelegramBot getTelegramBot(String token, String name) {
        TelegramBot bot = botFactory.getNewBot();
        bot.setToken(token);
        bot.setName(name);
        return bot;
    }
}
