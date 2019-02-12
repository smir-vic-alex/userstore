package com.smirix.senders.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.VKSenderBase;
import com.smirix.senders.auth.requests.AuthActorRq;
import com.smirix.senders.auth.requests.AuthActorRs;

import java.io.IOException;

/**
 * Created by Виктор on 07.01.2019.
 */
public class AuthActorSender extends VKSenderBase<AuthActorRq, AuthActorRs> {

    private static final String REST_URL = "/rest/service/vk/actor/auth/code";

    @Override
    protected Message<AuthActorRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<AuthActorRs>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
