package com.smirix.senders.queries;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.senders.queries.requests.PostRq;
import com.smirix.senders.queries.requests.PostRs;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;

/**
 * Created by Виктор on 08.01.2019.
 */
public class CreatePostSender extends JsonHttpSender<PostRq, PostRs> {

    public CreatePostSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class getType() {
        return CreatePostSender.class;
    }

    @Override
    protected Message<PostRs> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<PostRs>>(){});
    }
}
