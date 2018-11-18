package hibernate;

import org.hibernate.Session;

/**
 * Created by Виктор on 15.05.2017.
 */
public interface HibernateAction<T> {
    public T execute(Session session);
}
