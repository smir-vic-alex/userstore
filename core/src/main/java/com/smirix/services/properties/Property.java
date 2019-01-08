package com.smirix.services.properties;

/**
 * Created by Виктор on 04.01.2019.
 */
public class Property {
    private Long id;
    private String key;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
