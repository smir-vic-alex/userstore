package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.senders.requests.ChannelRq;
import com.smirix.senders.requests.Channels;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;

/**
 * Created by Виктор on 21.01.2019.
 */
public class GetUserTelegramChannelsSender extends JsonHttpSender<ChannelRq, Channels> {

    public GetUserTelegramChannelsSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class getType() {
        return GetUserTelegramChannelsSender.class;
    }

    @Override
    protected Message<Channels> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<Channels>>(){});
    }
}
