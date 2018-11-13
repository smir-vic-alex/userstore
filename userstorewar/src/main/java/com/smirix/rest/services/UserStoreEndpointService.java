package com.smirix.rest.services;

import com.smirix.entities.login.Login;
import com.smirix.entities.password.Password;
import com.smirix.entities.user.User;
import com.smirix.rest.builders.AddUserRsBuilder;
import com.smirix.rest.builders.GetUserByIdRsBuilder;
import com.smirix.rest.builders.GetUserByLoginRsBuilder;
import com.smirix.services.LoginStoreService;
import com.smirix.services.PasswordStoreService;
import com.smirix.services.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

/**
 * Сервис по работе с пользователями
 * Created by Виктор on 30.09.2018.
 */
public class UserStoreEndpointService {
    @Autowired
    @Qualifier("userStoreService")
    private UserStoreService userStoreService;

    @Autowired
    @Qualifier("loginStoreService")
    private LoginStoreService loginStoreService;

    @Autowired
    @Qualifier("passwordService")
    private PasswordStoreService passwordStoreService;

    public GetUserByLoginRs getUserByLogin(GetUserByLoginRq getUserByLoginRq) {
        try {
            Login login = loginStoreService.getLoginByLoginName(getUserByLoginRq.getLogin());
            User user = userStoreService.getUserById(login.getUserId());

            return new GetUserByLoginRsBuilder(login, user).success();
        } catch (Exception e) {
            e.printStackTrace();

            return new GetUserByLoginRsBuilder(e).fail();
        }
    }

    public GetUserByIdRs getUserById(GetUserByIdRq getUserByIdRq) {
        try {
            User user = userStoreService.getUserById(getUserByIdRq.getId().longValue());

            return new GetUserByIdRsBuilder(user).success();
        } catch (Exception e) {
            return new GetUserByIdRsBuilder(e).fail();
        }
    }

    public AddUserRs addUser(AddUserRq addUserRq) {
        try {
            User user = new User();
            user.setName(addUserRq.getUser().getName());
            user.setSurName(addUserRq.getUser().getSurName());
            user.setMiddleName(addUserRq.getUser().getSecondName());
            userStoreService.saveOrUpdate(user);

            Login login = new Login();
            login.setLogin(addUserRq.getLogin().getLogin());
            login.setUserId(user.getId());
            loginStoreService.saveOrUpdate(login);

            com.smirix.entities.password.Password passwd = new Password();
            passwd.setPassword(addUserRq.getPassword().getPassword());
            passwd.setUserId(user.getId());
            passwordStoreService.saveOrUpdate(passwd);

            return new AddUserRsBuilder(user, login).success();
        } catch (Exception e) {
            return new AddUserRsBuilder(e).fail();
        }
    }
}
