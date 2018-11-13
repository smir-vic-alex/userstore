package com.smirix.services;

import com.smirix.hibernate.HibernateExecutor;

/**
 * Created by Виктор on 01.10.2018.
 */
public class BusinessService<T> {

    private Executor executor;

    public BusinessService(Executor executor) {
        this.executor = executor;
    }


    public void saveOrUpdate(final T object) {
        executor.executeSaveOrUpdate(object, object.getClass());
    }

    public T getById(final Long id, final Class<T> clazz) {
        return executor.getById(id, clazz);
    }

    public T getByKey(final String key, final Class<T> clazz) {
        return executor.getByKey(key, clazz);
    }
}
