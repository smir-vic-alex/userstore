package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;

import java.io.IOException;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-08
 */
public class SchedulerRemovePostSender extends SchedulerSenderBase<Long, Long> {

    private static final String REST_URL = "/rest/service/scheduler/remove/post";

    @Override
    protected Message<Long> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<Long>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
