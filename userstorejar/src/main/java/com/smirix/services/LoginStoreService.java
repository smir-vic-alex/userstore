package com.smirix.services;

import com.smirix.entities.login.Login;

/**
 * Created by Виктор on 31.10.2018.
 */
public class LoginStoreService extends BusinessService<Login> {

    public LoginStoreService(Executor executor) {
        super(executor);
    }

    public Login getLoginByLoginName(final String loginName) {
        return super.getByKey(loginName, Login.class);
    }
}
