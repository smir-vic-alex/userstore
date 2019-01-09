package com.smirix.senders.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.senders.auth.requests.AuthActorRq;
import com.smirix.senders.auth.requests.AuthActorRs;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;

/**
 * Created by Виктор on 07.01.2019.
 */
public class AuthActorSender extends JsonHttpSender<AuthActorRq, AuthActorRs> {

    public AuthActorSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class getType() {
        return AuthActorSender.class;
    }

    @Override
    protected Message<AuthActorRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<AuthActorRs>>(){});
    }

    @Override
    protected Message<AuthActorRs> buildResponse(Object message) throws Exception {
        Message<AuthActorRs> msg =  super.buildResponse(message);

//        if (msg.getHead().getCode() != 0)
//            throw new RuntimeException("Ошибка при добавлении ВК пользователя");

        return msg;
    }
}
