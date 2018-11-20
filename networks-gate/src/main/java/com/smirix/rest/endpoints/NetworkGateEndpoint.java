package com.smirix.rest.endpoints;

import com.smirix.rest.services.NetworkGateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.GetAuthNetRedirectUrlRq;
import ru.json2pojo.beans.GetAuthNetRedirectUrlRs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/gate/network")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NetworkGateEndpoint {

    @Autowired
    @Qualifier("networkGateService")
    private NetworkGateService networkGateService;

    @POST
    @Path("/get/auth/redirect/url")
    public GetAuthNetRedirectUrlRs getAuthRedirectUrl(GetAuthNetRedirectUrlRq rq) {
        return null;
    }
}
