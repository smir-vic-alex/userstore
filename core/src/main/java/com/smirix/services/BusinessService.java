package com.smirix.services;

import com.smirix.hibernate.HibernateExecutor;

/**
 * Created by Виктор on 01.10.2018.
 */
public class BusinessService {

    public <T> void saveOrUpdate(Object object, Class<T> type){
        new HibernateExecutor<T>().execute((session) ->
                {
                    session.saveOrUpdate(object);
                    return null;
                }
        );
    }

    public <T> T getById(Long id, Class<T> type) {
        return new HibernateExecutor<T>().execute((session) ->
                {
                    return session.get(type, id);
                }
        );
    }

    public <T> T getByKey(String key, Class<T> type) {
        return new HibernateExecutor<T>().execute((session) ->
                {
                    return session.get(type, key);
                }
        );
    }
}
