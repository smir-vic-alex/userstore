package com.smirix.rest.endpoints;

import com.smirix.rest.services.SystemAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Виктор on 15.11.2018.
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SystemAuthEndpoint {

    @Autowired
    @Qualifier("systemAuthService")
    private SystemAuthService systemAuthService;

    @POST
    @Path("/login")
    public Response login(LoginRq loginRq) {
        return systemAuthService.login(loginRq);
    }

    @POST
    @Path("/register")
    public RegisterRs register(RegisterRq registerRq) {
        return systemAuthService.register(registerRq);
    }

    @POST
    @Path("/logoff")
    public Response logoff(LogoffRq logoffRq) {
        return systemAuthService.logoff(logoffRq);
    }
}
