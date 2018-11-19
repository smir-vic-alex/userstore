package com.smirix.senders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirix.rest.elements.messages.Head;
import com.smirix.rest.elements.messages.Message;
import com.smirix.rest.elements.messages.MessageRq;
import com.smirix.rest.elements.messages.MessageRs;

import java.io.IOException;

public class BaseSender extends JsonHttpSender<MessageRq, MessageRs> {

    public <T> MessageRs<T> send(MessageRq request) {
        buildHeader(request);
        return fromJson(super.send(request, request.getUrl()));
    }

    private void buildHeader(Message request) {
        Head head = new Head();
        head.setCode("codeTest");
        head.setMessage("messageTest");
        head.setSuccess(true);
        head.setSystemFrom("systemFromTest");
        head.setSystemTo("systemToTest");
        head.setTime("timeTest");

        request.setHead(head);
    }

    private <T> MessageRs<T> fromJson(String json) {
        try {
            return new ObjectMapper().<MessageRs<T>>readValue(json, new TypeReference<MessageRs<T>>(){});
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
