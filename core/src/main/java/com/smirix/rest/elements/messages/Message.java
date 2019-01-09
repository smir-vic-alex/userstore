package com.smirix.rest.elements.messages;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Message {
    private Head head;
    private Object body;
    private Error error;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
