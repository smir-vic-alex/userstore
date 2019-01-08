package com.smirix.rest.endpoints;

import com.smirix.rest.services.VKServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Виктор on 04.01.2019.
 */
@Path("/service/vk")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VKServiceEndpoint {

    @Autowired
    @Qualifier("vkServiceService")
    private VKServiceService vkServiceService;

    //todo Проверять на подлинность источника запросов для всех модулей
    @POST
    @Path("/get/auth/url")
    public GetAuthUrlRs getAuthUrl(GetAuthUrlRq rq) {
        return vkServiceService.getAuthUrl(rq);
    }

    @POST
    @Path("/actor/auth/code")
    public ActorAuthByCodeRs actorAuthBuCode(ActorAuthByCodeRq rq) {
        return vkServiceService.actorAuthByCode(rq);
    }

    @POST
    @Path("/get/user/groups")
    public GetUserGroupsRs getUserGroups(GetUserGroupsRq rq) {
        return vkServiceService.getUserGroups(rq);
    }

    @POST
    @Path("/create/post")
    public CreatePostRs createPost(CreatePostRq rq) {
        return vkServiceService.createPost(rq);
    }
}
