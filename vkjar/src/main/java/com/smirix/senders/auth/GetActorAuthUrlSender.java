package com.smirix.senders.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.VKSenderBase;
import com.smirix.senders.auth.requests.GetActorAuthUrl;

import java.io.IOException;

/**
 * Created by Виктор on 06.01.2019.
 */
public class GetActorAuthUrlSender extends VKSenderBase<GetActorAuthUrl, GetActorAuthUrl> {

    private static final String REST_URL = "/rest/service/vk/get/auth/url";

    protected Message<GetActorAuthUrl> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<GetActorAuthUrl>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
