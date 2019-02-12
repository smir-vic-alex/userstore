package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.requests.ChannelRq;
import com.smirix.senders.requests.Channels;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class GetUserTelegramChannelsSender extends TelegramSenderBase<ChannelRq, Channels> {

    private static final String REST_URL = "/rest/service/tlgm/get/user/channels";

    @Override
    protected Message<Channels> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<Channels>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
