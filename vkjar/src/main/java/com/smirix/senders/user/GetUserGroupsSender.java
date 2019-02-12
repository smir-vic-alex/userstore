package com.smirix.senders.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.entities.VKGroup;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.VKSenderBase;
import com.smirix.senders.user.requests.UserGroupsRq;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Виктор on 08.01.2019.
 */
public class GetUserGroupsSender extends VKSenderBase<UserGroupsRq, ArrayList<VKGroup>> {

    private static final String REST_URL = "/rest/service/vk/get/user/groups";

    @Override
    protected Message<ArrayList<VKGroup>> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<ArrayList<VKGroup>>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
