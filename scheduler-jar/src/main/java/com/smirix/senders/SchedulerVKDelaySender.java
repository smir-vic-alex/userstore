package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.requests.VKDelayPostRq;
import com.smirix.requests.VKDelayPostRs;
import com.smirix.rest.elements.messages.Message;

import java.io.IOException;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-12
 */
public class SchedulerVKDelaySender extends SchedulerSenderBase<VKDelayPostRq, VKDelayPostRs> {

    private static final String REST_URL = "/rest/service/scheduler/delay/vk/post";

    @Override
    protected Message<VKDelayPostRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<VKDelayPostRs>>(){});
    }

    @Override
    protected String getUrl() {
        return REST_URL;
    }
}
