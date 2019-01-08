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

}
