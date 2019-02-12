package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.requests.Bots;
import com.smirix.senders.requests.BotsRq;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class GetUserTelegramBotsSender extends TelegramSenderBase<BotsRq, Bots> {

    private static final String REST_URL = "/rest/service/tlgm/get/user/bots";

    @Override
    protected Message<Bots> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<Bots>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
