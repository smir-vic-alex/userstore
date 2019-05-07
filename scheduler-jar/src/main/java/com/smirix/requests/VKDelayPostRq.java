package com.smirix.requests;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-04
 */
public class VKDelayPostRq implements Serializable {
    private Long userId;
    private Long taskId;
    private Integer ownerId;
    private String message;
    private Boolean fromGroup;
    private List<String> attachments;
    private String publishDate;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(Boolean fromGroup) {
        this.fromGroup = fromGroup;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
