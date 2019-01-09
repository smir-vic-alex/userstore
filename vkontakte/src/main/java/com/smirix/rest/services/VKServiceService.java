package com.smirix.rest.services;

import com.smirix.entities.ActorType;
import com.smirix.entities.VKGroup;
import com.smirix.entities.VKGroupActor;
import com.smirix.entities.VKUserActor;
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
        ActorType actorType = urlRq.getBody().getActorType();

        GetAuthUrlRs rs = new GetAuthUrlRs();
        rs.setBody(urlRq.getBody());
        rs.setHead(urlRq.getHead());
        rs.getBody().setUrl(vkApiSetting.getAuthUrl(actorType, urlRq.getBody().getIds()));

        return rs;
    }

    public ActorAuthByCodeRs actorAuthByCode(ActorAuthByCodeRq rq) {
        ActorAuthByCodeRs rs = new ActorAuthByCodeRs();
        rs.setHead(rq.getHead());

        AuthActorRq authActorRq = rq.getBody();
        Object vkRs = actorHelper.authActor(authActorRq);
        actorHelper.saveOrUpdateVkCode(authActorRq, vkRs);

        return rs;
    }

    public GetUserGroupsRs getUserGroups(GetUserGroupsRq rq) {
        UserGroupsRq groupsRq = rq.getBody();
        GetUserGroupsRs rs = new GetUserGroupsRs();
        rs.setHead(rq.getHead());

        VKUserActor vkUserActor = vkService.getVKUserNetworkByUserId(groupsRq.getUserId());
        List<GroupFull> vkGroups = vkConnectorManager.getGroups(vkUserActor);


        if (groupsRq.getLinked()) {
            List<VKGroupActor> groups = vkService.getVKGroupNetworksByUserId(groupsRq.getUserId());
            //todo убрать из бд группы, которые не пришли из списка
            List<GroupFull> filteredList = new ArrayList<>();
            for (VKGroupActor actor : groups) {
                for (GroupFull vkGroup : vkGroups) {
                    if (vkGroup.getId().equals(actor.getVkUserId().toString())) {
                        filteredList.add(vkGroup);
                    }
                }
            }
            vkGroups = filteredList;
        }

        rs.setBody(convertToVKGroupList(vkGroups));
        return rs;
    }

    public CreatePostRs createPost(CreatePostRq rq) {
        PostRq post = rq.getBody();

        VKUserActor userNetwork = vkService.getVKUserNetworkByUserId(post.getUserId());

        Integer postId = vkConnectorManager.createPost(getUserActor(userNetwork), -post.getOwnerId(), post.getMessage(), post.getPublishDate());
        CreatePostRs rs = new CreatePostRs();
        rs.setHead(rq.getHead());

        PostRs postRs = new PostRs();
        postRs.setPostId(postId);
        rs.setBody(postRs);

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
