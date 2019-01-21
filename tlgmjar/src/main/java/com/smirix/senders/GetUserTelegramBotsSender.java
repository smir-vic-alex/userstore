package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.senders.requests.Bots;
import com.smirix.senders.requests.BotsRq;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class GetUserTelegramBotsSender extends JsonHttpSender<BotsRq, Bots> {

    public GetUserTelegramBotsSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class getType() {
        return GetUserTelegramBotsSender.class;
    }

    @Override
    protected Message<Bots> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<Bots>>(){});
    }
}
