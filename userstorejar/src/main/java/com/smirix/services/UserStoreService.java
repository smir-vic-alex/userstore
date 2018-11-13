package com.smirix.services;

import com.smirix.entities.login.Login;
import com.smirix.entities.user.User;
import com.smirix.hibernate.HibernateExecutor;
import org.hibernate.query.Query;

/**
 * Created by Виктор on 02.10.2018.
 */
public class UserStoreService extends BusinessService<User> {

    public UserStoreService(Executor executor) {
        super(executor);
    }

    public User getUserById(Long userId) {
        return super.getById(userId, User.class);
    }

    public User addUser(User user) {
        return new User();
    }

    public Login addLogin(Login login) {
        return new Login();
    }
}
