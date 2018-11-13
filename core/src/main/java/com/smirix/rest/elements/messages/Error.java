package com.smirix.rest.elements.messages;

/**
 * Created by Виктор on 02.10.2018.
 */
public class Error {
    private Long code;
    private String description;

    public Error(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
