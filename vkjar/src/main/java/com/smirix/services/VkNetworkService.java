package com.smirix.services;

import com.smirix.entities.ActorType;
import com.smirix.entities.VKGroup;
import com.smirix.entities.VKUser;
import com.smirix.senders.auth.AuthActorSender;
import com.smirix.senders.auth.GetActorAuthUrlSender;
import com.smirix.senders.auth.requests.AuthActorRq;
import com.smirix.senders.auth.requests.GetActorAuthUrl;
import com.smirix.senders.queries.CreatePostSender;
import com.smirix.senders.queries.requests.PostRq;
import com.smirix.senders.user.GetUserGroupsSender;
import com.smirix.senders.user.GetUserSender;
import com.smirix.senders.user.requests.UserGroupsRq;
import com.smirix.senders.user.requests.UserRq;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Виктор on 06.01.2019.
 */
public class VkNetworkService {

    private GetActorAuthUrlSender getActorAuthUrlSender;
    private AuthActorSender authActorSender;
    private GetUserGroupsSender getUserGroupsSender;
    private GetUserSender getUserSender;
    private CreatePostSender createPostSender;

    public void setCreatePostSender(CreatePostSender createPostSender) {
        this.createPostSender = createPostSender;
    }

    public void setGetUserGroupsSender(GetUserGroupsSender getUserGroupsSender) {
        this.getUserGroupsSender = getUserGroupsSender;
    }

    public void setGetUserSender(GetUserSender getUserSender) {
        this.getUserSender = getUserSender;
    }

    public void setGetActorAuthUrlSender(GetActorAuthUrlSender getActorAuthUrlSender) {
        this.getActorAuthUrlSender = getActorAuthUrlSender;
    }

    public void setAuthActorSender(AuthActorSender authActorSender) {
        this.authActorSender = authActorSender;
    }

    public String getUserActorAuthUrl() {
        return getActorAuthUrl(ActorType.USER, null);
    }

    public String getGroupActorAuthUrl(String... ids) {
        return getActorAuthUrl(ActorType.GROUP, Arrays.asList(ids));
    }

    private String getActorAuthUrl(ActorType actor, List<String> ids) {
        try {
            GetActorAuthUrl rq = new GetActorAuthUrl();
            rq.setActorType(actor);
            rq.setIds(ids);

            return getActorAuthUrlSender.send(rq).getUrl();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void authActor(String code, Long userId, ActorType actorType) {
        try {
            AuthActorRq rq = new AuthActorRq();
            rq.setActorType(actorType);
            rq.setUserId(userId);
            rq.setCode(code);

            authActorSender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<VKGroup> getUserGroups(Long userId) {
        try {
            UserGroupsRq rq = new UserGroupsRq();
            rq.setUserId(userId);
            rq.setFromVK(false);

            return getUserGroupsSender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public VKUser getUser(Long userId) {
        try {
            UserRq rq = new UserRq();
            rq.setUserId(userId);

            return getUserSender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void createPost(Long userId, Integer groupId, String message, List<String> attachments, Integer publishDate, Boolean fromGroup) {
        try {
            PostRq rq = new PostRq();
            rq.setUserId(userId);
            rq.setOwnerId(groupId);
            rq.setMessage(message);
            rq.setAttachments(attachments);
            rq.setPublishDate(publishDate);
            rq.setFromGroup(fromGroup);

            createPostSender.send(rq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
