package com.smirix.services;

import com.smirix.entities.password.Password;

/**
 * Created by Виктор on 31.10.2018.
 */
public class PasswordStoreService extends BusinessService<Password> {

    public PasswordStoreService(Executor executor) {
        super(executor);
    }
}
