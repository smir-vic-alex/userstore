package com.smirix.rest.services;

import com.smirix.entities.*;
import com.smirix.rest.elements.messages.Status;
import com.smirix.rest.helpers.ActorHelper;
import com.smirix.senders.auth.requests.AuthActorRq;
import com.smirix.senders.queries.requests.PostRq;
import com.smirix.senders.queries.requests.PostRs;
import com.smirix.senders.user.requests.UserGroupsRq;
import com.smirix.services.VKConnectorManager;
import com.smirix.services.VKUtils;
import com.smirix.services.VkService;
import com.smirix.settings.VKApiSetting;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Виктор on 04.01.2019.
 */
public class VKServiceService {

    @Autowired
    @Qualifier("vkApiSetting")
    private VKApiSetting vkApiSetting;

    @Autowired
    @Qualifier("actorHelper")
    private ActorHelper actorHelper;

    @Autowired
    @Qualifier("vkService")
    private VkService vkService;

    @Autowired
    @Qualifier("vkConnectorManager")
    private VKConnectorManager vkConnectorManager;

    public GetAuthUrlRs getAuthUrl(GetAuthUrlRq urlRq) {

        GetAuthUrlRs rs = new GetAuthUrlRs();
        rs.setHead(urlRq.getHead());

        try {
            rs.setBody(urlRq.getBody());
            ActorType actorType = urlRq.getBody().getActorType();
            rs.getBody().setUrl(vkApiSetting.getAuthUrl(actorType, urlRq.getBody().getIds()));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(-1L, e.getMessage()));
        }

        return rs;
    }

    public ActorAuthByCodeRs actorAuthByCode(ActorAuthByCodeRq rq) {
        ActorAuthByCodeRs rs = new ActorAuthByCodeRs();
        rs.setHead(rq.getHead());

        try {
            AuthActorRq authActorRq = rq.getBody();
            Object vkRs = actorHelper.authActor(authActorRq);
            actorHelper.saveOrUpdateVkCode(authActorRq, vkRs);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(-1L, e.getMessage()));
        }

        return rs;
    }

    public GetUserRs getUser(GetUserRq rq) {
        GetUserRs rs = new GetUserRs();
        rs.setHead(rq.getHead());

        VKUserActor actor = vkService.getVKUserNetworkByUserId(rq.getBody().getUserId());
        VKUser vkUser = new VKUser();
        vkUser.setId(actor.getId());
        vkUser.setUserId(actor.getUserId());
        vkUser.setVkUserId(actor.getVkUserId());
        rs.setBody(vkUser);

        return rs;
    }

    public GetUserGroupsRs getUserGroups(GetUserGroupsRq rq) {

        GetUserGroupsRs rs = new GetUserGroupsRs();
        rs.setHead(rq.getHead());

        try {
            UserGroupsRq groupsRq = rq.getBody();
            VKUserActor vkUserActor = vkService.getVKUserNetworkByUserId(groupsRq.getUserId());
            List<GroupFull> vkGroups = vkConnectorManager.getGroups(vkUserActor);


//            if (groupsRq.getLinked()) {
//                List<VKGroupActor> groups = vkService.getVKGroupNetworksByUserId(groupsRq.getUserId());
//                //todo убрать из бд группы, которые не пришли из списка
//                List<GroupFull> filteredList = new ArrayList<>();
//                for (VKGroupActor actor : groups) {
//                    for (GroupFull vkGroup : vkGroups) {
//                        if (vkGroup.getId().equals(actor.getVkUserId().toString())) {
//                            filteredList.add(vkGroup);
//                        }
//                    }
//                }
//                vkGroups = filteredList;
//            }

            rs.setBody(convertToVKGroupList(vkGroups));
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(-1L, e.getMessage()));
        }
        return rs;
    }

    public CreatePostRs createPost(CreatePostRq rq) {

        CreatePostRs rs = new CreatePostRs();
        rs.setHead(rq.getHead());

        try {
            PostRq post = rq.getBody();
            VKUserActor userNetwork = vkService.getVKUserNetworkByUserId(post.getUserId());
            Integer postId = vkConnectorManager.createPost(getUserActor(userNetwork), -post.getOwnerId(), post.getMessage(), post.getPublishDate());

            PostRs postRs = new PostRs();
            postRs.setPostId(postId);
            rs.setBody(postRs);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(-1L, e.getMessage()));
        }

        return rs;
    }

    private UserActor getUserActor(VKUserActor vkUserActor) {
        return new UserActor(vkUserActor.getVkUserId(), vkUserActor.getVkAccessCode());
    }

    private List<VKGroup> convertToVKGroupList(List<GroupFull> groupFulls)
    {
        List<VKGroup> vkGroupList = new ArrayList<>(groupFulls.size());
        for (GroupFull group : groupFulls) {
            VKGroup vkGroup = new VKGroup();

            vkGroup.setId(group.getId());
            vkGroup.setName(group.getName());
            vkGroup.setUrlPhoto(group.getPhoto50());
            vkGroupList.add(vkGroup);
        }
        return vkGroupList;
    }

}
