package com.smirix.rest.services;

import com.smirix.BusinessConfig;
import com.smirix.BotService;
import com.smirix.messages.TelegramUserWrapper;
import com.smirix.pojo.TelegramChannel;
import com.smirix.pojo.TelegramUser;
import com.smirix.rest.MessageUtils;
import com.smirix.senders.requests.Bots;
import com.smirix.senders.requests.Channels;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.*;

import java.util.List;

/**
 * Сервис-обработчик запросов в админскую часть
 */
public class TelegramService {

    private static final String INVALID_COMMAND_MESSAGE = "Invalid AddCommandRq request";
    private static final String INVALID_CREATE_BOT_MESSAGE = "Invalid CreateBotRq request";
    private static Logger LOGGER = LoggerFactory.getLogger(TelegramService.class);

    @Autowired
    private BotService botService;

    @Autowired
    private BusinessConfig config;

    /**
     * Создать и зарегестрировать новый инстанс вики бота
     *
     * @param createBotRq - запрос на создание бота
     * @return ответ об успешности пользователю
     */
    public DefaultAnswer createBot(CreateBotRq createBotRq) {
        LOGGER.debug("Start create bot");

        try {
            validate(createBotRq);
            botService.register(createBotRq.getBody().getUserId(), createBotRq.getBody().getToken(), createBotRq.getBody().getName());
        } catch (Throwable e) {
            LOGGER.error("Error create bot", e);
            return MessageUtils.fail(createBotRq.getHead());
        }

        LOGGER.debug("Success create bot");
        return MessageUtils.success(createBotRq.getHead());
    }

    public DefaultAnswer createChannel(CreateChannelRq rq) {
        LOGGER.debug("Start create bot");

        try {
            validate(rq);
            botService.registerChannel(rq.getBody().getUserId(), rq.getBody().getChannelName(), rq.getBody().getBotId());

            LOGGER.debug("Success create bot");
            DefaultAnswer rs = new DefaultAnswer();
            rs.setHead(rq.getHead());
            return rs;
        } catch (Throwable e) {
            LOGGER.error("Error create bot", e);
            return null;
        }
    }

    private void validate(CreateChannelRq createChannelRq) {

    }

    /**
     * Добавить/обновить команду с ответом
     *
     * @param addCommandRq - запрос на создание команды
     * @return ответ об успешности пользователю
     */
    public DefaultAnswer addCommand(AddCommandRq addCommandRq) {
        LOGGER.debug("Start create command");

        try {
            validate(addCommandRq);
            config.getCommandsMap().put(addCommandRq.getCommand(), addCommandRq.getValue());
        } catch (Throwable e) {
            LOGGER.error("Error create command", e);
            return null;
        }

        LOGGER.debug("Success create command");
        return null;
    }

    public DefaultAnswer sendMessage(SendMessageToChannelRq rq) {
        LOGGER.debug("Start send message");

        try {
            validate(rq);
            botService.sendMessage(rq.getBody().getUserId(), rq.getBody().getMessage(), rq.getBody().getChannelName());
            DefaultAnswer answer = new DefaultAnswer();
            answer.setHead(rq.getHead());

            LOGGER.debug("Success send message");
            return answer;
        } catch (Throwable e) {
            LOGGER.error("Error send message", e);
            return null;
        }
    }

    public GetUserBotRs getUserBots(GetUserBotsRq rq) {
        LOGGER.debug("Start get user bots");

        try {
            validate(rq);
            List<TelegramUser> bots = botService.getUserBots(rq.getBody().getUserId());

            GetUserBotRs rs = new GetUserBotRs();
            rs.setHead(rq.getHead());
            Bots botsRs = new Bots();
            botsRs.setBots(bots);
            rs.setBody(botsRs);
            LOGGER.debug("Success get user bots");
            return rs;
        } catch (Throwable e) {
            LOGGER.error("Error send message", e);
            return null;
        }
    }

    public GetUserChannelsRs getUserChannels(GetUserChannelsRq rq) {
        LOGGER.debug("Start get user bots");

        try {
            validate(rq);
            List<TelegramChannel> channels = botService.getUserChannelsByUserId(rq.getBody().getUserId());

            GetUserChannelsRs rs = new GetUserChannelsRs();
            rs.setHead(rq.getHead());
            Channels channelsRs = new Channels();
            channelsRs.setChannels(channels);
            rs.setBody(channelsRs);
            LOGGER.debug("Success get user bots");
            return rs;
        } catch (Throwable e) {
            LOGGER.error("Error send message", e);
            return null;
        }
    }

    private void validate(GetUserChannelsRq rq) {

    }

    private void validate(GetUserBotsRq rq) {

    }


    private void validate(SendMessageToChannelRq rq) {

    }

    private void validate(AddCommandRq addCommandRq) {
        if (StringUtils.isNotEmpty(addCommandRq.getCommand()))
            if (addCommandRq.getCommand().startsWith("/"))
                return;
        LOGGER.error(INVALID_COMMAND_MESSAGE);
        throw new RuntimeException(INVALID_COMMAND_MESSAGE);

    }

    private void validate(CreateBotRq createBotRq) {
        if (StringUtils.isEmpty(createBotRq.getBody().getName()) || StringUtils.isEmpty(createBotRq.getBody().getToken())) {
            LOGGER.error(INVALID_CREATE_BOT_MESSAGE);
            throw new RuntimeException(INVALID_CREATE_BOT_MESSAGE);
        }
    }
}
