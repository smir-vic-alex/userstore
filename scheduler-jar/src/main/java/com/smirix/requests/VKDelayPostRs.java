package com.smirix.requests;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-04
 */
public class VKDelayPostRs implements Serializable {
    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
