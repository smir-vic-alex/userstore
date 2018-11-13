package com.smirix.rest.endpoints;

import com.smirix.rest.services.UserStoreEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Endpoint`ы сервиса
 * Created by Виктор on 30.09.2018.
 */
@Path("/user/store")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserStoreEndpoint {

    @Autowired
    @Qualifier("userStoreEndpointService")
    private UserStoreEndpointService userStoreEndpointService;

    @POST
    @Path("/get/id")
    public GetUserByIdRs getUserById(GetUserByIdRq getUserByIdRq) {
        return userStoreEndpointService.getUserById(getUserByIdRq);
    }

    @POST
    @Path("/add")
    public AddUserRs addUser(AddUserRq addUserRq) {
        return userStoreEndpointService.addUser(addUserRq);
    }

    @POST
    @Path("/get/login")
    public GetUserByLoginRs getUserByLogin(GetUserByLoginRq getUserByLoginRq) {
        return userStoreEndpointService.getUserByLogin(getUserByLoginRq);
    }
}
