package com.smirix.requests;

import java.io.Serializable;
import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-20
 */
public class GetDelayedPostsRs implements Serializable {
    private Long userId;
    private List<DelayedPostRs> delayedPostRs;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<DelayedPostRs> getDelayedPostRs() {
        return delayedPostRs;
    }

    public void setDelayedPostRs(List<DelayedPostRs> delayedPostRs) {
        this.delayedPostRs = delayedPostRs;
    }
}
