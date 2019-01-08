package com.smirix.entities;

/**
 * Created by Виктор on 07.01.2019.
 */
public enum ActorType {

    USER("user"),
    GROUP("group"),
    SERVICE("service");

    private String description;

    ActorType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
