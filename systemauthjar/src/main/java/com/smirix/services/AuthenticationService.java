package com.smirix.services;

import com.smirix.entities.Login;
import com.smirix.entities.Node;
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
                    } catch (Exception e) {
                        LOGGER.error(ERROR_MSG, e);
                        return null;
                    }
                }
        );
    }

    public Password getPasswordById(Long userId) {
        return new HibernateExecutor<Password>().execute((session) ->
                {
                    try {
                        Query<Password> query = session.createNamedQuery("com.smirix.entities.Password.getPasswdById", Password.class);
                        query.setParameter("userId", userId);
                        return query.getSingleResult();
                    } catch (Exception e) {
                        LOGGER.error(ERROR_MSG, e);
                        return null;
                    }
                }
        );
    }

    public void saveToken(Token token) {
        saveOrUpdate(token, Token.class);
    }

    public Token getToken(String token) {
        return new HibernateExecutor<Token>().execute((session) ->
                {
                    try {
                        Query<Token> query = session.createNamedQuery("com.smirix.entities.Token.getToken", Token.class);
                        query.setParameter("token", token);
                        return query.getSingleResult();
                    } catch (Exception e) {
                        LOGGER.error(ERROR_MSG, e);
                        return null;
                    }
                }
        );
    }

    public Node getUserNodeByUserId(Long userId) {
        return new HibernateExecutor<Node>().execute((session) ->
                {
                    try {
                        Query<Node> query = session.createNamedQuery("com.smirix.entities.Node.getUserNode", Node.class);
                        query.setParameter("userId", userId);
                        return query.getSingleResult();
                    } catch (Exception e) {
                        LOGGER.error(ERROR_MSG, e);
                        return null;
                    }
                }
        );
    }

    public void remove(Token token) {
        new HibernateExecutor<Token>().execute((session) ->
            {
                try {
                    session.delete(token);
                    return null;
                } catch (Exception e) {
                    LOGGER.error(ERROR_MSG, e);
                    return null;
                }
            }
        );
    }
}
