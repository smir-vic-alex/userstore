package com.smirix.senders.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.senders.auth.requests.GetActorAuthUrl;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;

/**
 * Created by Виктор on 06.01.2019.
 */
public class GetActorAuthUrlSender extends JsonHttpSender<GetActorAuthUrl, GetActorAuthUrl> {

    public GetActorAuthUrlSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class<GetActorAuthUrlSender> getType() {
        return GetActorAuthUrlSender.class;
    }

    protected Message<GetActorAuthUrl> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<GetActorAuthUrl>>(){});
    }
}
