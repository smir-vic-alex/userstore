package com.smirix.services;

import com.smirix.entities.Login;
import com.smirix.entities.Password;
import com.smirix.entities.Token;
import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * Created by Виктор on 15.11.2018.
 */
public class AuthenticationService extends BusinessService {

    public Login getLoginByName(String login) {
        return new HibernateExecutor<Login>().execute((session) ->
                {
                    try {
                        Query<Login> query = session.createNamedQuery("com.smirix.entities.login.Login.getLoginByLoginName", Login.class);
                        query.setParameter("login", login);
                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }

    public Password getPasswordByUserId(Long userId) {
        return new HibernateExecutor<Password>().execute((session) ->
                {
                    try {
                        Query<Password> query = session.createNamedQuery("com.smirix.entities.Password.getPasswdById", Password.class);
                        query.setParameter("userId", userId);
                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }

    public void saveToken(Token token) {
        saveOrUpdate(token, Token.class);
    }
}
