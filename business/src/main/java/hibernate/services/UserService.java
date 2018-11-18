package hibernate.services;

import entities.Login;
import entities.User;
import hibernate.BusinessService;
import hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * Created by Виктор on 08.09.2017.
 */
public class UserService extends BusinessService<User> {

    public static final String QUERY_NAME_USER_BY_LOGIN_ID = "entities.User.get.user.by.login.id";

    public User getUserByLogin(final Login login){
        return new HibernateExecutor<User>().execute((session) -> {
            try {
                Query<User> query = session.createNamedQuery(QUERY_NAME_USER_BY_LOGIN_ID, User.class);
                query.setParameter("loginId", login.getId());
                return query.getSingleResult();
            }
            catch (NoResultException e) {
                return null;
            }
        });
    }
}
