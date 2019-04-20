package com.smirix.services;

import com.smirix.hibernate.HibernateExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Виктор on 01.10.2018.
 */
public class BusinessService {

    protected static Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);
    protected static final String ERROR_MSG = "Ошибка!";

    public <T> void saveOrUpdate(Object object, Class<T> type){
        new HibernateExecutor<T>().execute((session) ->
                {
                    session.saveOrUpdate(object);
                    return null;
                }
        );
    }

}
