package com.smirix.services;

/**
 * Created by Виктор on 02.11.2018.
 */
public interface Executor {

    <T> void executeSaveOrUpdate(Object object, Class<T> type);

    <T> T getById(Long id, Class<T> type);

    <T> T getByKey(String key, Class<T> type);
}
