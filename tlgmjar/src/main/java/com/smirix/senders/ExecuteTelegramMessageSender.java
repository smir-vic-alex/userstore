package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.requests.DefaultAnswer;
import com.smirix.senders.requests.ExecuteMessage;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ExecuteTelegramMessageSender extends TelegramSenderBase<ExecuteMessage, DefaultAnswer> {

    private static final String REST_URL = "/rest/service/tlgm/send/message";

    @Override
    protected String getUrl() {
        return REST_URL;
    }

    @Override
    protected Message<DefaultAnswer> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<DefaultAnswer>>(){});
    }
}
