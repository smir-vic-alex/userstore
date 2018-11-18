package property;

import org.hibernate.query.Query;
import hibernate.BusinessService;
import hibernate.HibernateExecutor;

import javax.persistence.NoResultException;

/**
 * Created by Виктор on 17.05.2017.
 */
public class PropertyService extends BusinessService<Property> {

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
