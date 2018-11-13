package com.smirix.hibernate;

import org.hibernate.Session;

/**
 * Created by Виктор on 01.10.2018.
 */
public interface HibernateAction<T> {
    public T execute(Session session);
}
