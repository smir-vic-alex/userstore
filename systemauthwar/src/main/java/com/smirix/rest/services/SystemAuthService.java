package com.smirix.rest.services;

import com.smirix.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import javax.ws.rs.core.Response;

/**
 * Created by Виктор on 15.11.2018.
 */
public class SystemAuthService {

    @Autowired
    @Qualifier("authenticationService")
    private AuthenticationService authenticationService;


    public LoginRs login(LoginRq loginRq) {
        return new LoginRs();
    }

    public Response logoff(LogoffRq logoffRq) {
        return Response.ok().build();
    }

    public RegisterRs register(RegisterRq registerRq) {
        return new RegisterRs();
    }
}
