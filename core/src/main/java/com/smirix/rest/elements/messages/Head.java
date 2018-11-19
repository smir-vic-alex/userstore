package com.smirix.rest.elements.messages;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Head {
    private String time;
    private String systemFrom;
    private String systemTo;
    private String code;
    private String message;
    private Boolean success;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystemFrom() {
        return systemFrom;
    }

    public void setSystemFrom(String systemFrom) {
        this.systemFrom = systemFrom;
    }

    public String getSystemTo() {
        return systemTo;
    }

    public void setSystemTo(String systemTo) {
        this.systemTo = systemTo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
