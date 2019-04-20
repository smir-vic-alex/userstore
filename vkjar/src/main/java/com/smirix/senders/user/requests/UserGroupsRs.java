package com.smirix.senders.user.requests;

import com.smirix.entities.DelayedVKPost;
import com.smirix.entities.VKGroup;

import java.io.Serializable;
import java.util.List;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-04-20
 */
public class UserGroupsRs implements Serializable {

    private List<VKGroup> groups;
    private List<DelayedVKPost> delayedVKPosts;

    public List<VKGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<VKGroup> groups) {
        this.groups = groups;
    }

    public List<DelayedVKPost> getDelayedVKPosts() {
        return delayedVKPosts;
    }

    public void setDelayedVKPosts(List<DelayedVKPost> delayedVKPosts) {
        this.delayedVKPosts = delayedVKPosts;
    }
}
