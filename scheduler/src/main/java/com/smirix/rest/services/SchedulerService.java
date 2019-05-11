package com.smirix.rest.services;

import com.smirix.common.Pair;
import com.smirix.entities.Attachment;
import com.smirix.entities.DelayTask;
import com.smirix.entities.DelayedPost;
import com.smirix.entities.TaskStatus;
import com.smirix.requests.*;
import com.smirix.rest.elements.messages.Status;
import com.smirix.services.DelayPostService;
import com.smirix.settings.SchedulerSetting;
import com.smirix.utils.DateUtils;
import com.smirix.utils.FileHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-04
 */
public class SchedulerService {

    private static Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private DelayPostService delayPostService;

    @Autowired
    @Qualifier("schedulerSetting")
    private SchedulerSetting schedulerSetting;

    public VkCreatePostRs delayVKCreatePost(VkCreatePostRq rq) {
        VkCreatePostRs rs = new VkCreatePostRs();
        rs.setHead(rq.getHead());

        try {
            rs.setStatus(new Status(0L, "ok"));

            VKDelayPostRs postRs = new VKDelayPostRs();
            VKDelayPostRq postRq = rq.getBody();
            Long taskId = postRq.getTaskId();
            if (taskId == null) {
                String fileLocalPath = schedulerSetting.getPrivateFilePath() + postRq.getUserId() + "/";

                File directory = new File(fileLocalPath);

                if (!directory.exists()) {
                    if(!directory.mkdirs()){
                        LOGGER.error("Не удалось создать директорию " + fileLocalPath);
                    }
                }

                String externalPath = schedulerSetting.getExternalFilePath() + postRq.getUserId() + "/";
                List<Attachment> attachments = null;
                List<File> files = null;
                if (MapUtils.isNotEmpty(postRq.getAttachments())) {
                    attachments = new ArrayList<>(postRq.getAttachments().size());
                    files = new ArrayList<>(postRq.getAttachments().size());

                    for (Map.Entry<String, String> entry : postRq.getAttachments().entrySet()) {

                        String name = entry.getKey();
                        String privateUrl = fileLocalPath + name;
                        String externalUrl = externalPath + name;
                        files.add(FileHelper.createFileFromBase64(privateUrl, entry.getValue()));

                        Attachment attachment = new Attachment();
                        attachment.setPrivateUrl(privateUrl);
                        attachment.setExternalUrl(externalUrl);
                        attachment.setName(name);

                        attachments.add(attachment);

                    }
                }

                DelayTask delayTask = delayPostService.delayPost(postRq, attachments);

                if (delayTask != null) {
                    postRs.setPostId(delayTask.getId());
                } else if (CollectionUtils.isNotEmpty(files)) {
                    for (File file : files) {
                        if(!file.delete()) {
                            LOGGER.error("Не удалось удалить файл " + file.getAbsolutePath());
                        }
                    }
                }
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

                    DelayTask task = pair.getFirst();
                    DelayedPost post = pair.getSecond();
                    DelayedPostRs postRs = convert(task, post);

                    delayedPostRs.add(postRs);
                }
            }
        }

        GetDelayedPostsRs postsRs = new GetDelayedPostsRs();
        postsRs.setDelayedPostRs(delayedPostRs);
        postsRs.setUserId(body.getUserId());
        rs.setBody(postsRs);
        return rs;
    }

    private List<AttachmentDto> convert(List<Attachment> allAttachmentsByUserId) {

        if (CollectionUtils.isNotEmpty(allAttachmentsByUserId)) {
            List<AttachmentDto> dtoList = new ArrayList<>(allAttachmentsByUserId.size());

            for (Attachment attachment : allAttachmentsByUserId) {
                AttachmentDto dto = new AttachmentDto();
                dto.setId(attachment.getId());
                dto.setExternalUrl(attachment.getExternalUrl());
                dto.setPrivateUrl(attachment.getPrivateUrl());
                dto.setName(attachment.getName());

                dtoList.add(dto);
            }

            return dtoList;
        }
        return Collections.emptyList();
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
        delayedPostRs.setAttachments(convert(delayPostService.getByPostId(post.getId())));

        return delayedPostRs;
    }

    public SchedulerRemovePostRs removePost(SchedulerRemovePostRq rq) {

        SchedulerRemovePostRs rs = new SchedulerRemovePostRs();
        if (rq.getBody() != null) {
            DelayTask task = delayPostService.getTaskById(rq.getBody());
            if (task != null) {
                task.setStatus(TaskStatus.DELETED.getValue());
                delayPostService.saveOrUpdateTask(task);
                rs.setBody(rq.getBody());
            }
        }

        rs.setHead(rq.getHead());
        rs.setStatus(new Status(0L, "ok"));

        return rs;
    }
}
