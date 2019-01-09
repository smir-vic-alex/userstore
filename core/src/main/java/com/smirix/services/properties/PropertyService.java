package com.smirix.services.properties;

import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * Created by Виктор on 04.01.2019.
 */
public class PropertyService {

    public Property getPropertyByKey(final String key) {
        return new HibernateExecutor<Property>().execute((session) ->
                {
                    try {
                        Query<Property> query = session.createNamedQuery("com.smirix.services.properties.get.by.key", Property.class);
                        query.setParameter("key", key);
                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }
}
