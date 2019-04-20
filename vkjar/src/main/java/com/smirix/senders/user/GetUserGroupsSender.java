package com.smirix.senders.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.VKSenderBase;
import com.smirix.senders.user.requests.UserGroupsRq;
import com.smirix.senders.user.requests.UserGroupsRs;

import java.io.IOException;

/**
 * Created by Виктор on 08.01.2019.
 */
public class GetUserGroupsSender extends VKSenderBase<UserGroupsRq, UserGroupsRs> {

    private static final String REST_URL = "/rest/service/vk/get/user/groups";

    @Override
    protected Message<UserGroupsRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<UserGroupsRs>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
