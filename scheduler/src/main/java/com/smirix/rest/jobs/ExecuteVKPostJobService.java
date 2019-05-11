package com.smirix.rest.jobs;

import com.smirix.entities.Attachment;
import com.smirix.entities.DelayTask;
import com.smirix.entities.DelayedPost;
import com.smirix.entities.TaskStatus;
import com.smirix.services.DelayPostService;
import com.smirix.services.VkNetworkService;
import com.smirix.settings.SchedulerSetting;
import com.smirix.utils.DateUtils;
import com.smirix.utils.FileHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.*;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-10
 */
public class ExecuteVKPostJobService {

    @Autowired
    private DelayPostService delayPostService;

    @Autowired
    @Qualifier("schedulerSetting")
    private SchedulerSetting schedulerSetting;

    @Autowired
    private VkNetworkService vkNetworkService;

    public void doJob() {

        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();

        fromDate.add(Calendar.MINUTE, -schedulerSetting.getTaskDateFromSearch());
        toDate.add(Calendar.MINUTE, schedulerSetting.getTaskDateToSearch());

        List<DelayTask> tasks = delayPostService.getTask(fromDate, toDate, TaskStatus.PREPARED.getValue());

        for (DelayTask task : tasks) {
            long diff = DateUtils.getDiffDateAndCurrentDateInMinutes(task.getFireDate());
            if (diff > 0 && diff < schedulerSetting.getDiffMinutesBeforePost()) {
                DelayedPost post = delayPostService.getDelayedPost(task.getDelayPostId(), task.getUserId());

                if (post != null) {
                    Map<String, String> attachments = new HashMap<>();
                    if (CollectionUtils.isNotEmpty(post.getAttachments())) {
                        for (Attachment attachment : post.getAttachments()) {

                            attachments.put(attachment.getName(), FileHelper.convertFileToBase64(new File(attachment.getPrivateUrl())));
                        }
                    }

                    vkNetworkService.createPost(task.getUserId(),
                            null,
                            task.getOwnerId().intValue(),
                            post.getMessage(),
                            attachments,
                            DateUtils.dateToString(task.getFireDate()),
                            post.getFromGroup(),
                            true);

                    task.setStatus(TaskStatus.DONE.getValue());
                    delayPostService.saveOrUpdate(task, DelayTask.class);
                }
            }
        }
    }
}
