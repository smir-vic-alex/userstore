package com.smirix.rest.elements.messages;

import java.io.Serializable;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Message<B extends Serializable> implements Serializable {
    private Head head;
    private B body;
    private Status status;

    public Message() {
    }

    public Message(Head head, Status status) {
        this.head = head;
        this.status = status;
    }

    public Message(Head head, B body) {
        this.head = head;
        this.body = body;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public B getBody() {
        return body;
    }

    public void setBody(B body) {
        this.body = body;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
