package com.smirix.senders.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.entities.VKGroup;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.json.JsonHttpSender;
import com.smirix.settings.SenderSettingBase;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Виктор on 08.01.2019.
 */
public class GetUserGroupsSender extends JsonHttpSender<Long, ArrayList<VKGroup>> {
    public GetUserGroupsSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    @Override
    protected Class getType() {
        return GetUserGroupsSender.class;
    }

    @Override
    protected Message<ArrayList<VKGroup>> fromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Message<ArrayList<VKGroup>>>(){});
    }
}
