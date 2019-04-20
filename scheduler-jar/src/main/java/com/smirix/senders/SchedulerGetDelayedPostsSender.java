package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.requests.GetDelayedPostsRq;
import com.smirix.requests.GetDelayedPostsRs;
import com.smirix.rest.elements.messages.Message;

import java.io.IOException;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-20
 */
public class SchedulerGetDelayedPostsSender extends SchedulerSenderBase<GetDelayedPostsRq, GetDelayedPostsRs> {

    private static final String REST_URL = "/rest/service/scheduler/get/all/vk/delayed/posts";

    @Override
    protected Message<GetDelayedPostsRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<GetDelayedPostsRs>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
