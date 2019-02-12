package com.smirix.rest.elements.messages;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Head implements Serializable {

    private Long code;
    private String scUrl;
    private Calendar time;
    private String uuid;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getScUrl() {
        return scUrl;
    }

    public void setScUrl(String url) {
        this.scUrl = url;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
