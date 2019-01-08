package com.smirix.senders.auth.requests;

import com.smirix.entities.ActorType;

import java.io.Serializable;

/**
 * Created by Виктор on 07.01.2019.
 */
public class AuthActorRq implements Serializable {
    private Long userId;
    private ActorType actorType;
    private String code;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ActorType getActorType() {
        return actorType;
    }

    public void setActorType(ActorType actorType) {
        this.actorType = actorType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
