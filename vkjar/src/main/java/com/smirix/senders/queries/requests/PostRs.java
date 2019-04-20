package com.smirix.senders.queries.requests;

import java.io.Serializable;

/**
 * Created by Виктор on 08.01.2019.
 */
public class PostRs implements Serializable {
    private Integer postId;
    private String description;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
