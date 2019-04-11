package com.smirix.rest.services;

import com.smirix.requests.VKDelayPostRs;
import com.smirix.rest.elements.messages.Status;
import com.smirix.services.DelayPostService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json2pojo.beans.VkCreatePostRq;
import ru.json2pojo.beans.VkCreatePostRs;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-04
 */
public class SchedulerService {

    @Autowired
    private DelayPostService delayPostService;

    public VkCreatePostRs delayVKCreatePost(VkCreatePostRq rq) {
        VkCreatePostRs rs = new VkCreatePostRs();
        rs.setHead(rq.getHead());

        try {
            rs.setStatus(new Status(0L, "ok"));

            VKDelayPostRs postRs = new VKDelayPostRs();

            postRs.setPostId(delayPostService.delayPost(rq.getBody()).getId());
            rs.setBody(postRs);

        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(0L, "ok"));
        }

        return rs;
    }
}
