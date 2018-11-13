package com.smirix.rest.builders;

import com.smirix.entities.login.Login;
import com.smirix.entities.user.User;
import com.smirix.rest.elements.messages.Error;
import ru.json2pojo.beans.GetUserByLoginRs;

/**
 * Created by Виктор on 02.10.2018.
 */
public class GetUserByLoginRsBuilder implements ResponseBuilder<GetUserByLoginRs> {

    private Login login;
    private User user;
    private String errorMessage;

    public GetUserByLoginRsBuilder(Login login, User user) {
        this.login = login;
        this.user = user;
    }

    public GetUserByLoginRsBuilder(Exception e) {
        this.errorMessage = e.getMessage();
    }

    @Override
    public GetUserByLoginRs success() {
        GetUserByLoginRs rs = new GetUserByLoginRs();
        rs.setLogin(login);
        rs.setUser(user);

        return rs;
    }

    @Override
    public GetUserByLoginRs fail() {
        GetUserByLoginRs rs = new GetUserByLoginRs();
        rs.setError(new Error(-1L, errorMessage));

        return rs;
    }
}
