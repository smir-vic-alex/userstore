package com.smirix.rest.services;

import com.smirix.common.Pair;
import com.smirix.entities.DelayTask;
import com.smirix.entities.DelayedPost;
import com.smirix.entities.TaskStatus;
import com.smirix.requests.*;
import com.smirix.rest.elements.messages.Status;
import com.smirix.services.DelayPostService;
import com.smirix.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json2pojo.beans.VkCreatePostRq;
import ru.json2pojo.beans.VkCreatePostRs;
import ru.json2pojo.beans.VkGetAllDelayedPostsRq;
import ru.json2pojo.beans.VkGetAllDelayedPostsRs;

import java.util.ArrayList;
import java.util.List;

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
            VKDelayPostRq postRq = rq.getBody();
            Long taskId = postRq.getTaskId();
            if (taskId == null) {
                postRs.setPostId(delayPostService.delayPost(postRq).getId());
            } else {

                DelayTask delayTask = delayPostService.getTaskById(taskId);

                if (delayTask != null) {
                    DelayedPost delayedPost = delayPostService.getDelayedPost(delayTask.getDelayPostId(), delayTask.getUserId());

                    if (delayedPost != null) {
                        delayTask.setFireDate(DateUtils.getDate(postRq.getPublishDate()));
                        delayedPost.setMessage(postRq.getMessage());
                        delayedPost.setFromGroup(postRq.getFromGroup());
                        postRs.setPostId(delayPostService.updateDelayedPost(delayedPost, delayTask).getId());
                    }
                }
            }
            rs.setBody(postRs);

        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(0L, "ok"));
        }

        return rs;
    }

    public VkGetAllDelayedPostsRs getAllDelayedPosts(VkGetAllDelayedPostsRq rq) {
        VkGetAllDelayedPostsRs rs = new VkGetAllDelayedPostsRs();
        rs.setHead(rq.getHead());
        rs.setStatus(new Status(0L, "ok"));

        GetDelayedPostsRq body = rq.getBody();
        List<DelayedPostRs> delayedPostRs = new ArrayList<>();
        for (Long ownerId : body.getGroupIds()) {
            List<Pair<DelayTask, DelayedPost>> pairs = delayPostService.getUserDelayedTask(body.getUserId(), ownerId, "VK", TaskStatus.PREPARED.getValue());
            if (CollectionUtils.isNotEmpty(pairs)) {
                for (Pair<DelayTask, DelayedPost> pair : pairs) {
                    delayedPostRs.add(convert(pair.getFirst(), pair.getSecond()));
                }
            }
        }
        GetDelayedPostsRs postsRs = new GetDelayedPostsRs();
        postsRs.setDelayedPostRs(delayedPostRs);
        postsRs.setUserId(body.getUserId());
        rs.setBody(postsRs);
        return rs;
    }

    private DelayedPostRs convert(DelayTask task, DelayedPost post) {
        DelayedPostRs delayedPostRs = new DelayedPostRs();

        delayedPostRs.setFireDate(DateUtils.dateToString(task.getFireDate()));
        delayedPostRs.setOwnerId(task.getOwnerId().intValue());
        delayedPostRs.setStatus(task.getStatus());
        delayedPostRs.setType(task.getType());
        delayedPostRs.setMessage(post.getMessage());
        delayedPostRs.setFromGroup(post.getFromGroup());
        delayedPostRs.setTaskId(task.getId());

        return delayedPostRs;
    }
}
