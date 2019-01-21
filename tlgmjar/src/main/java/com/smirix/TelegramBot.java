package com.smirix;

import com.smirix.messages.MessageBotResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


/**
 * Реализация телеграм бота
 * Created by Виктор on 08.03.2018.
 */
public class TelegramBot extends TelegramLongPollingBot {

    public static final String TELEGRAM_API_EXCEPTION_MESSAGE = "TelegramApiException with update: ";
    private static Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private String token;
    private String name;

    @Autowired
    private MessageBotResolver resolver;

    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {

    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(resolver.resolve(update));
        } catch (TelegramApiException e) {
            LOGGER.error(TELEGRAM_API_EXCEPTION_MESSAGE + update.toString(), e);
        }
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error(TELEGRAM_API_EXCEPTION_MESSAGE + message.toString(), e);
        }
    }

    public String getBotUsername() {
        return name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(String name) {
        this.name = name;
    }
}
