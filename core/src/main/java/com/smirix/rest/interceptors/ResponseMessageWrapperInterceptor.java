package com.smirix.rest.interceptors;

import com.smirix.rest.elements.messages.Head;
import com.smirix.rest.elements.messages.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;

/**
 * Created by Виктор on 28.10.2018.
 */
public class ResponseMessageWrapperInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Object entity = context.getEntity();
        Message msg = new Message();
        msg.setBody(entity);
        msg.setHead(new Head());
        context.setEntity(msg);
        context.setType(Message.class);
        context.setGenericType(Message.class);
    }
}
