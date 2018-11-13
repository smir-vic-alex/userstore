package com.smirix.rest.builders;

import com.smirix.entities.login.Login;
import com.smirix.entities.user.User;
import com.smirix.rest.elements.messages.Error;
import ru.json2pojo.beans.AddUserRs;

/**
 * Created by Виктор on 31.10.2018.
 */
public class AddUserRsBuilder implements ResponseBuilder<AddUserRs> {

    private User user;
    private Login login;
    private String errorMessage;

    public AddUserRsBuilder(User user, Login login) {
        this.user = user;
        this.login = login;
    }

    public AddUserRsBuilder(Exception e) {
        this.errorMessage = e.getMessage();
    }

    @Override
    public AddUserRs success() {
        AddUserRs rs = new AddUserRs();
        rs.setUser(user);
        rs.setLogin(login);

        return rs;
    }

    @Override
    public AddUserRs fail() {
        AddUserRs rs = new AddUserRs();
        rs.setError(new Error(-1L, errorMessage));

        return rs;
    }
}
