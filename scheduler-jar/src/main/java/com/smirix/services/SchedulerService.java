package com.smirix.services;

import com.smirix.requests.GetDelayedPostsRq;
import com.smirix.requests.GetDelayedPostsRs;
import com.smirix.requests.VKDelayPostRq;
import com.smirix.senders.SchedulerGetDelayedPostsSender;
import com.smirix.senders.SchedulerRemovePostSender;
import com.smirix.senders.SchedulerVKDelaySender;

import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-12
 */
public class SchedulerService {

    private SchedulerVKDelaySender vkDelaySender;
    private SchedulerGetDelayedPostsSender schedulerGetDelayedPostsSender;
    private SchedulerRemovePostSender schedulerRemovePostSender;

    public void setVkDelaySender(SchedulerVKDelaySender vkDelaySender) {
        this.vkDelaySender = vkDelaySender;
    }

    public void setSchedulerGetDelayedPostsSender(SchedulerGetDelayedPostsSender schedulerGetDelayedPostsSender) {
        this.schedulerGetDelayedPostsSender = schedulerGetDelayedPostsSender;
    }

    public void setSchedulerRemovePostSender(SchedulerRemovePostSender schedulerRemovePostSender) {
        this.schedulerRemovePostSender = schedulerRemovePostSender;
    }

    public Long removePost(Long taskId) {
        try {
            return schedulerRemovePostSender.send(taskId);
        } catch (Exception e) {
            return null;
        }
    }

    public void delayVKPost(VKDelayPostRq rq) {
        try {
            vkDelaySender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GetDelayedPostsRs getAllUserDelayedPosts(Long userId, List<Long> groupIds) {
        try {
            GetDelayedPostsRq rq = new GetDelayedPostsRq();
            rq.setUserId(userId);
            rq.setGroupIds(groupIds);
            return schedulerGetDelayedPostsSender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
