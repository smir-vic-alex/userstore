package com.smirix.rest.elements.messages;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Message<T> {
    private Head head;
    private String url;
    private T body;
    private Error error;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
