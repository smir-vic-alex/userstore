package com.smirix.rest.elements.messages;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Head implements Serializable {

    private String scUrl;
    private Calendar time;
    private String uuid;

    /**
     * @return url запроса
     */
    public String getScUrl() {
        return scUrl;
    }

    public void setScUrl(String url) {
        this.scUrl = url;
    }

    /**
     * @return время запроса
     */
    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    /**
     * @return уникальный идентификатор запроса
     */
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
