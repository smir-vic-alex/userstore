package com.smirix.entities;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-14
 */
public enum TaskStatus {
    PREPARED("PREPARED"),
    INPROCESS("INPROCESS"),
    DONE("DONE"),
    DELETED("DELETED"),
    ERROR("ERROR");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
