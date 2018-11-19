package com.smirix.services;

import com.smirix.entities.Property;
import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * Created by Виктор on 17.05.2017.
 */
public class PropertyService extends BusinessService {

    public Property getPropertyByKey(final String key) {
        return new HibernateExecutor<Property>().execute((session) ->
                {
                    try {
                        Query<Property> query = session.createNamedQuery("property.Property.get.by.key", Property.class);
                        query.setParameter("key", key);
                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }

}
