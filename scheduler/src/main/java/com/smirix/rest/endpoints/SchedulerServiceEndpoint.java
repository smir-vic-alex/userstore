package com.smirix.rest.endpoints;

import com.smirix.rest.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.VkCreatePostRq;
import ru.json2pojo.beans.VkCreatePostRs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-04
 */
@Path("/service/scheduler")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SchedulerServiceEndpoint {

    @Autowired
    @Qualifier("schedulerService")
    private SchedulerService schedulerService;

    @POST
    @Path("/delay/vk/post")
    public VkCreatePostRs scheduleVKPost(VkCreatePostRq rq) {
        return schedulerService.delayVKCreatePost(rq);
    }
}