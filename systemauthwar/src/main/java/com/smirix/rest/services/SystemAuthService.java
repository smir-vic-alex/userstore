package com.smirix.rest.services;

import com.smirix.entities.Login;
import com.smirix.entities.Node;
import com.smirix.entities.Password;
import com.smirix.entities.Token;
import com.smirix.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.UUID;

import static javax.ws.rs.core.Cookie.DEFAULT_VERSION;
import static javax.ws.rs.core.NewCookie.DEFAULT_MAX_AGE;

/**
 * Created by Виктор on 15.11.2018.
 */
public class SystemAuthService {

    @Autowired
    @Qualifier("authenticationService")
    private AuthenticationService authenticationService;

    public Response login(LoginRq loginRq) {
        try {
            Login login = authenticationService.getLoginByName(loginRq.getLogin());
            if (login == null)
                throw new RuntimeException("Неправильный логин");

            Password password = authenticationService.getPasswordByUserId(login.getUserId());
            if (password == null || !password.getPassword().equals(loginRq.getPassword()))
                throw new RuntimeException("Неправильный пароль");

            Token token = getToken(login);

            authenticationService.saveToken(token);

            return getResponse(token);
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private Response getResponse(Token token) throws Exception {
        LoginRs rs = new LoginRs();
        Node node = authenticationService.getUserNodeByUserId(token.getUserId());
        rs.setRedirectUrl(node.getUrl());//"http://localhost:8081/auth.do"
        NewCookie cookie = new NewCookie("token", token.getToken(), "/", null, DEFAULT_VERSION, null, DEFAULT_MAX_AGE, null, false, false);
        return Response.ok(rs).cookie(cookie).build();
    }

    private Token getToken(Login login) {
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUserId(login.getUserId());
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.DAY_OF_MONTH, 1);
        token.setExpired(expired);
        return token;
    }

    public Response logoff(LogoffRq logoffRq) {
        return Response.ok().build();
    }

    public RegisterRs register(RegisterRq registerRq) {
        return new RegisterRs();
    }
}
