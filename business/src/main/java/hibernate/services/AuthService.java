package hibernate.services;

import entities.Password;
import hibernate.BusinessService;
import entities.Login;
import hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * Created by SBT-Smirnov-VA on 20.07.2017.
 */
public class AuthService extends BusinessService {

    public Login findByLogin(final String login) {
        return new HibernateExecutor<Login>().execute((session) ->
                {
                    try {
                        Query<Login> query = session.createNamedQuery("entities.Login.get.login.by.login", Login.class);
                        query.setParameter("login", login);
                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }

    public Password findPasswordByLoginId(final Long loginId){
        return new HibernateExecutor<Password>().execute((session)->{
           try {
               Query<Password> query = session.createNamedQuery("entities.Password.get.password.by.login.id", Password.class);
               query.setParameter("loginId", loginId);
               return query.getSingleResult();
           }catch (Exception e)
           {
               return null;
           }
        });
    }
}
