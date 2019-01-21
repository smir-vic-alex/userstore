package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.senders.requests.DefaultAnswer;
import com.smirix.senders.requests.ExecuteMessage;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ExecuteTelegramMessageSender extends JsonHttpSender<ExecuteMessage, DefaultAnswer> {
    public ExecuteTelegramMessageSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class getType() {
        return ExecuteTelegramMessageSender.class;
    }

    @Override
    protected Message<DefaultAnswer> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<DefaultAnswer>>(){});
    }
}
