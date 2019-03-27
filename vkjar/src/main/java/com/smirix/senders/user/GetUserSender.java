package com.smirix.senders.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.entities.VKUser;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.VKSenderBase;
import com.smirix.senders.user.requests.UserRq;

import java.io.IOException;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-03-28
 */
public class GetUserSender extends VKSenderBase<UserRq, VKUser> {

    private static final String REST_URL = "/rest/service/vk/get/user";

    @Override
    protected Message<VKUser> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<VKUser>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
