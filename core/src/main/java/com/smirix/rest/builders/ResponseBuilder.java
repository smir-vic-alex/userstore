package com.smirix.rest.builders;

/**
 * Created by Виктор on 02.10.2018.
 */
public interface ResponseBuilder<T> {
    T success();
    T fail();
}
