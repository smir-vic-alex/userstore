package com.smirix.rest.services;

import com.smirix.entities.Login;
import com.smirix.entities.Node;
import com.smirix.entities.Password;
import com.smirix.entities.Token;
import com.smirix.services.AuthenticationService;
import com.smirix.utils.CookieHelper;
import com.smirix.utils.PasswordUtils;
import com.smirix.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


/**
 * Created by Виктор on 15.11.2018.
 */
public class SystemAuthService {

    private static final String WRONG_PASSWD_MESSAGE = "Неправильный пароль";
    private static final String WRONG_LOGIN_MESSAGE = "Не найден запрашиваемый логин";
    private static final String ACCESS_DENIED_MSG = "Доступ запрещен";

    private static Logger LOGGER = LoggerFactory.getLogger(SystemAuthService.class);

    @Autowired
    @Qualifier("authenticationService")
    private AuthenticationService authenticationService;

    public Response login(LoginRq loginRq) {
        try {

            Login login = authenticationService.getLoginByName(loginRq.getLogin());

            if (login == null) {
                LOGGER.error(WRONG_LOGIN_MESSAGE);
                throw new RuntimeException(WRONG_LOGIN_MESSAGE);
            }

            Password password = authenticationService.getPasswordById(login.getId());

            if (PasswordUtils.isNotValidPassword(loginRq.getPassword(), password.getHash())) {
                LOGGER.error(WRONG_PASSWD_MESSAGE);
                throw new RuntimeException(WRONG_PASSWD_MESSAGE);
            }

            Token token = TokenUtils.generateToken(login);
            authenticationService.saveToken(token);

            return getResponse(token);

        } catch (Exception e) {
            LOGGER.error(ACCESS_DENIED_MSG);
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private Response getResponse(Token token) {

        Node node = authenticationService.getUserNodeByUserId(token.getUserId());
        NewCookie cookie = CookieHelper.getCookie(token.getToken());

        LoginRs rs = new LoginRs();
        rs.setRedirectUrl(node.getUrl());

        return Response.ok(rs).cookie(cookie).build();
    }
}
