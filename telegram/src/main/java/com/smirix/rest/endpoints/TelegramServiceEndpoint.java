package com.smirix.rest.endpoints;

import com.smirix.rest.services.TelegramService;
import com.smirix.tlgm.EndpointBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json.schema2pojo.dto.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service/tlgm")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelegramServiceEndpoint extends EndpointBase<TelegramService> {

    @Autowired
    @Qualifier("telegramService")
    @Override
    public void setService(TelegramService service) {
        super.setService(service);
    }

    @Path("/add/bot")
    @POST
    public DefaultAnswer putToken(CreateBotRq createBotRq) {
        return getService().createBot(createBotRq);
    }

    @Path("/add/channel")
    @POST
    public DefaultAnswer addChannel(CreateChannelRq rq) {
        return getService().createChannel(rq);
    }

    @Path("/add/command")
    @POST
    public DefaultAnswer addCommand(AddCommandRq addCommandRq) {
        return getService().addCommand(addCommandRq);
    }

    @Path("/send/message")
    @POST
    public DefaultAnswer sendMessage(SendMessageToChannelRq rq) {
        return getService().sendMessage(rq);
    }

    @Path("/get/user/bots")
    @POST
    public GetUserBotRs getUserBots(GetUserBotsRq rq) {
        return getService().getUserBots(rq);
    }

    @Path("/get/user/channels")
    @POST
    public GetUserChannelsRs getUserBots(GetUserChannelsRq rq) {
        return getService().getUserChannels(rq);
    }
}
