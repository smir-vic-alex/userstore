package com.smirix.senders.requests;

import java.io.Serializable;

/**
 * Created by Виктор on 21.01.2019.
 */
public class DefaultAnswer implements Serializable {
    private String empty = "empty";

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }
}
