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
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
            createActor(authActorRq);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(new Status(-1L, e.getMessage()));
        }

        return rs;
    }

    private void createActor(AuthActorRq authActorRq) {
        String code = authActorRq.getCode();
        ActorType type = authActorRq.getActorType();
        Long userId = authActorRq.getUserId();

        if (ActorType.USER == type) {
            createVKUserConnector(code, userId);
        } else if (ActorType.GROUP == type) {
            createVKGroup(code, userId);
        } else {
            throw new RuntimeException("Неизвестный тип actorType " + type);
        }
    }

    private void createVKGroup(String code, Long userId) {
        GroupAuthResponse rs = vkConnectorManager.authGroup(code);

        VKUserActor vkUserActor = vkService.getVKUserNetworkByUserId(userId);
        List<GroupFull> vkGroups = vkConnectorManager.getGroups(vkUserActor, rs.getAccessTokens().keySet());

        for (Map.Entry<Integer, String> entry : rs.getAccessTokens().entrySet())
        {
            VKGroupActor vkGroupNetwork = actorHelper.getVkGroupActor(userId, entry);
            vkService.saveOrUpdate(vkGroupNetwork, VKGroupActor.class);

            for (GroupFull groupFull : vkGroups) {
                Long id = Long.parseLong(groupFull.getId());
                if (id.equals(vkGroupNetwork.getVkUserId().longValue())) {
                    VKGroup vkGroup = new VKGroup();
                    vkGroup.setUserId(userId);
                    vkGroup.setVkId(id);
                    vkGroup.setName(groupFull.getName());
                    vkGroup.setAvatarUrl(groupFull.getPhoto50());

                    vkService.saveOrUpdate(vkGroup, VKGroup.class);
                    break;
                }
            }
        }
    }

    private void createVKUserConnector(String code, Long userId) {
        UserAuthResponse rs = vkConnectorManager.authUser(code);
        VKUserActor vkUserActor = actorHelper.getVkUserActor(rs, userId);
        vkService.saveOrUpdate(vkUserActor, VKUserActor.class);
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

    public List<VKGroup> getUserGroupsFromVK(UserGroupsRq rq) {
        VKUserActor vkUserActor = vkService.getVKUserNetworkByUserId(rq.getUserId());
        List<GroupFull> vkGroups = vkConnectorManager.getGroups(vkUserActor);

        return convertToVKGroupList(vkGroups);
    }

    public GetUserGroupsRs getUserGroups(GetUserGroupsRq rq) {

        GetUserGroupsRs rs = new GetUserGroupsRs();
        rs.setHead(rq.getHead());

        try {
            UserGroupsRq groupsRq = rq.getBody();
            List<VKGroup> groups;
            if (groupsRq.getFromVK() != null && groupsRq.getFromVK()) {
                groups = getUserGroupsFromVK(groupsRq);
            } else {
                groups = vkService.getVKGroups(groupsRq.getUserId());
            }

            rs.setBody(groups);
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

    private List<VKGroup> convertToVKGroupList(List<GroupFull> groupFulls) {
        List<VKGroup> vkGroupList = new ArrayList<>(groupFulls.size());
        for (GroupFull group : groupFulls) {
            VKGroup vkGroup = new VKGroup();

            vkGroup.setId(Long.parseLong(group.getId()));
            vkGroup.setName(group.getName());
            vkGroup.setAvatarUrl(group.getPhoto50());
            vkGroupList.add(vkGroup);
        }
        return vkGroupList;
    }

}
