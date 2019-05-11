package com.smirix.entities;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-11
 */
public class PostToAttachment {
    private Long postId;
    private Long attachId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }
}
