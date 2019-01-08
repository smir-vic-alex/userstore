package com.smirix.senders.auth.requests;

import com.smirix.entities.ActorType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Виктор on 06.01.2019.
 */
public class GetActorAuthUrl implements Serializable {
    private ActorType actorType;
    private String url;
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public ActorType getActorType() {
        return actorType;
    }

    public void setActorType(ActorType actorType) {
        this.actorType = actorType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
