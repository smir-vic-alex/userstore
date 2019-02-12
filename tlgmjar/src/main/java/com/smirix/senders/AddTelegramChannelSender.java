package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.requests.AddChannel;
import com.smirix.senders.requests.DefaultAnswer;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class AddTelegramChannelSender extends TelegramSenderBase<AddChannel, DefaultAnswer> {

    private static final String REST_URL = "/rest/service/tlgm/add/channel";

    @Override
    protected Message<DefaultAnswer> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<DefaultAnswer>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
