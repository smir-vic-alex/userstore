package hibernate;

/**
 * Created by Виктор on 15.05.2017.
 */
public class BusinessService<T> {

    public <T> void saveOrUpdate(final T object) {
        new HibernateExecutor<T>().execute((session) ->
                {
                    session.saveOrUpdate(object);
                    return null;
                }
        );
    }

    public T getById(final Class<T> clazz, final Long id) {
        return new HibernateExecutor<T>().execute((session) ->
                {
                    return session.get(clazz, id);
                }
        );
    }

}
