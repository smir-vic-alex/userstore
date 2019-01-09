package com.smirix.services;

import com.smirix.entities.login.Login;
import com.smirix.entities.user.User;
import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * Created by Виктор on 02.10.2018.
 */
public class UserStoreService extends BusinessService {

    public User getUserById(Long userId){
        return new HibernateExecutor<User>().execute((session) -> {
            try {
                Query<User> query = session.createNamedQuery("com.smirix.entities.user.User.getUserById", User.class);
                query.setParameter("userId", userId);
                return query.getSingleResult();
            }
            catch (NoResultException e) {
                return null;
            }
        });
    }

    public User addUser(User user) {
        return new User();
    }

    public Login addLogin(Login login) {
        return new Login();
    }
}
