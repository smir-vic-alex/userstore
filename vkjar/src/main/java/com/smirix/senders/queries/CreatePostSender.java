package com.smirix.senders.queries;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.senders.VKSenderBase;
import com.smirix.senders.queries.requests.PostRq;
import com.smirix.senders.queries.requests.PostRs;

import java.io.IOException;

/**
 * Created by Виктор on 08.01.2019.
 */
public class CreatePostSender extends VKSenderBase<PostRq, PostRs> {

    private static final String REST_URL = "/rest/service/vk/create/post";

    @Override
    protected Message<PostRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<PostRs>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
