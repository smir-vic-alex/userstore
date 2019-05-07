package com.smirix.senders.queries.requests;


import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Виктор on 08.01.2019.
 */
public class PostRq implements Serializable {
    private Long taskId;
    private Long userId;
    private Integer ownerId;
    private String message;
    private Boolean fromGroup;
    private List<String> attachments;
    private String publishDate;
    private boolean isNotNeedCheckSchedule;

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

    public boolean isNotNeedCheckSchedule() {
        return isNotNeedCheckSchedule;
    }

    public void setNotNeedCheckSchedule(boolean notNeedCheckSchedule) {
        isNotNeedCheckSchedule = notNeedCheckSchedule;
    }
}
