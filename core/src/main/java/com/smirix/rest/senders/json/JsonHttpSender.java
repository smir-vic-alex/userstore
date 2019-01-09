package com.smirix.rest.senders.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.senders.ModuleSender;
import com.smirix.settings.SenderSettingBase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Виктор on 05.01.2019.
 */
public abstract class JsonHttpSender<Rq extends Serializable, Rs extends Serializable> extends ModuleSender<Rq, Rs> {

    public JsonHttpSender(SenderSettingBase senderSetting) {
        super(senderSetting);
    }

    protected HttpResponse execute(Message<Rq> message) throws Exception {
        StringEntity requestEntity = new StringEntity(serializeToJson(message), ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(message.getHead().getScUrl());
        postMethod.setEntity(requestEntity);

        return HttpClientBuilder.create().build().execute(postMethod);
    }

    @Override
    protected String postExecute(Object response) throws Exception {
        HttpResponse rawResponse = (HttpResponse) response;
        return EntityUtils.toString(rawResponse.getEntity(), JsonEncoding.UTF8.getJavaName());
    }

    @Override
    protected Message<Rs> buildResponse(Object message) throws Exception {
        String json = (String) message;
        return fromJson(json);
    }

    private String serializeToJson(Message<Rq> message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(message);
    }

    protected abstract Message<Rs> fromJson(String json) throws IOException;
}
