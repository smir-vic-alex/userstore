package com.smirix.rest.services;

import com.smirix.entities.*;
import com.smirix.requests.AttachmentDto;
import com.smirix.requests.DelayedPostRs;
import com.smirix.requests.GetDelayedPostsRs;
import com.smirix.requests.VKDelayPostRq;
import com.smirix.rest.elements.messages.Status;
import com.smirix.rest.helpers.ActorHelper;
import com.smirix.senders.auth.requests.AuthActorRq;
import com.smirix.senders.queries.requests.PostRq;
import com.smirix.senders.queries.requests.PostRs;
import com.smirix.senders.user.requests.UserGroupsRq;
import com.smirix.senders.user.requests.UserGroupsRs;
import com.smirix.services.SchedulerService;
import com.smirix.services.VKConnectorManager;
import com.smirix.services.VkService;
import com.smirix.settings.VKApiSetting;
import com.smirix.utils.DateUtils;
import com.smirix.utils.FileHelper;
import com.smirix.utils.StringUtils;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.photos.Photo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json2pojo.beans.*;

import java.io.File;
import java.util.*;


/**
 * Created by Виктор on 04.01.2019.
 */
public class VKServiceService {

    private static final String ERROR_MSG = "Ошибка!";
    private static Logger LOGGER = LoggerFactory.getLogger(VKServiceService.class);

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

    @Autowired
    @Qualifier("schedulerService")
    private SchedulerService schedulerService;

    public GetAuthUrlRs getAuthUrl(GetAuthUrlRq urlRq) {
        GetAuthUrlRs rs = new GetAuthUrlRs();
        rs.setHead(urlRq.getHead());

        try {
            rs.setBody(urlRq.getBody());
            ActorType actorType = urlRq.getBody().getActorType();
            rs.getBody().setUrl(vkApiSetting.getAuthUrl(actorType, urlRq.getBody().getIds()));
        } catch (Exception e) {
            LOGGER.error(ERROR_MSG, e);
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
            LOGGER.error(ERROR_MSG, e);
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
            LOGGER.error("Неизвестный тип actorType " + type);
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

    public List<VKGroup> getUserGroupsFromVK(Long userId) {
        VKUserActor vkUserActor = vkService.getVKUserNetworkByUserId(userId);
        List<GroupFull> vkGroups = vkConnectorManager.getGroups(vkUserActor);

        return convertToVKGroupList(vkGroups);
    }

    public GetUserGroupsRs getUserGroups(GetUserGroupsRq rq) {

        GetUserGroupsRs rs = new GetUserGroupsRs();
        rs.setHead(rq.getHead());

        try {
            UserGroupsRq groupsRq = rq.getBody();
            UserGroupsRs userGroupsRs = new UserGroupsRs();

            Long userId = groupsRq.getUserId();
            if (groupsRq.getFromVK() != null && groupsRq.getFromVK()) {
                userGroupsRs.setGroups(getUserGroupsFromVK(userId));
            } else {
                List<VKGroup> groups = vkService.getVKGroups(userId);
                userGroupsRs.setGroups(groups);
                userGroupsRs.setDelayedVKPosts(convert(groups, schedulerService.getAllUserDelayedPosts(userId, getListVKGroupIds(groups))));
            }

            rs.setBody(userGroupsRs);
        } catch (Exception e) {
            LOGGER.error(ERROR_MSG, e);
            rs.setStatus(new Status(-1L, e.getMessage()));
        }
        return rs;
    }

    private List<DelayedVKPost> convert(List<VKGroup> groups, GetDelayedPostsRs allUserDelayedPosts) {
        List<DelayedPostRs> delayedPosts = allUserDelayedPosts.getDelayedPostRs();

        if (CollectionUtils.isNotEmpty(delayedPosts)) {
            List<DelayedVKPost> delayedVKPostList = new ArrayList<>(delayedPosts.size());
            for (DelayedPostRs postRs : delayedPosts) {
                DelayedVKPost delayedVKPost = new DelayedVKPost();
                delayedVKPost.setFireDate(postRs.getFireDate());
                delayedVKPost.setFromGroup(postRs.getFromGroup());
                delayedVKPost.setMessage(postRs.getMessage());
                delayedVKPost.setOwnerId(postRs.getOwnerId());
                delayedVKPost.setStatus(postRs.getStatus());
                delayedVKPost.setType(postRs.getType());
                delayedVKPost.setTaskId(postRs.getTaskId());
                getGroupAvatar(delayedVKPost, groups);

                delayedVKPost.setAttachVKDtos(convertToVK(postRs.getAttachments()));

                delayedVKPostList.add(delayedVKPost);
            }

            return delayedVKPostList;
        }

        return Collections.emptyList();
    }

    private void getGroupAvatar(DelayedVKPost delayedVKPost, List<VKGroup> groups) {
        for (VKGroup group : groups) {
            if (delayedVKPost.getOwnerId().longValue() == group.getVkId()){
                delayedVKPost.setAvatarUrl(group.getAvatarUrl());
                delayedVKPost.setGroupName(group.getName());
            }
        }
    }

    private List<Long> getListVKGroupIds(List<VKGroup> groups) {
        List<Long> ids = new ArrayList<>(groups.size());
        for (VKGroup group : groups) {
            ids.add(group.getVkId());
        }

        return ids;
    }

    public CreatePostRs createPost(CreatePostRq rq) {
        CreatePostRs rs = new CreatePostRs();
        rs.setHead(rq.getHead());
        PostRs postRs = new PostRs();

        try {
            PostRq post = rq.getBody();
            VKUserActor userNetwork = vkService.getVKUserNetworkByUserId(post.getUserId());
            if(!post.isNotNeedCheckSchedule() && StringUtils.isNotEmpty(post.getPublishDate()) && isNeedToDelay(post.getPublishDate())) {

                VKDelayPostRq vkDelayPostRq = new VKDelayPostRq();
                vkDelayPostRq.setTaskId(post.getTaskId());
                vkDelayPostRq.setUserId(post.getUserId());
                vkDelayPostRq.setOwnerId(post.getOwnerId());
                vkDelayPostRq.setFromGroup(post.getFromGroup());
                vkDelayPostRq.setMessage(post.getMessage());
                vkDelayPostRq.setPublishDate(post.getPublishDate());
                vkDelayPostRq.setAttachments(post.getAttachments());

                schedulerService.delayVKPost(vkDelayPostRq);

                postRs.setDescription("Пост запланирован с датой исполнения " + post.getPublishDate() + ". За " + vkApiSetting.getDiffMinutesBeforePost() + " минут до исполнения система подготовит пост в группе");
            } else {
                Map<String, String> attachments = post.getAttachments();
                File file = null;

                if (MapUtils.isNotEmpty(attachments)) {
                    String path = vkApiSetting.getFilePath();
                    for (Map.Entry<String, String> entry : attachments.entrySet()) {
                        file = FileHelper.createFileFromBase64(path + entry.getKey(), entry.getValue());
                    }
                }

                UserActor userActor = getUserActor(userNetwork);
                List<String> preparedAttachments = new ArrayList<>();
                if (file != null) {
                    preparedAttachments = convert(vkConnectorManager.getNameLoadedPhoto(userActor, post.getOwnerId(), file));
                    if (!file.delete()) {
                        LOGGER.error("Не удалось удалить файл " + file.getAbsolutePath());
                    }
                }

                Integer time = StringUtils.isEmpty(post.getPublishDate()) ? 0 : DateUtils.getDiffDateAndCurrentDateInSeconds(post.getPublishDate());
                Integer postId = vkConnectorManager.createPost(userActor, -post.getOwnerId(), post.getMessage(), time, post.getFromGroup(), preparedAttachments);
                postRs.setPostId(postId);
            }

            rs.setBody(postRs);
        } catch (Exception e) {
            LOGGER.error(ERROR_MSG, e);
            rs.setStatus(new Status(-1L, e.getMessage()));
        }

        return rs;
    }

    private List<String> convert(List<Photo> nameLoadedPhoto) {

        List<String> converted = new ArrayList<>(nameLoadedPhoto.size());

        for (Photo loadedPhoto : nameLoadedPhoto) {
            converted.add("photo" + loadedPhoto.getOwnerId() + "_" + loadedPhoto.getId());
        }

        return converted;
    }

    private List<AttachVKDto> convertToVK(List<AttachmentDto> attachmentDtoList) {
        if (CollectionUtils.isEmpty(attachmentDtoList)) {
            return Collections.emptyList();
        }

        List<AttachVKDto> attachVKDtos = new ArrayList<>(attachmentDtoList.size());

        for (AttachmentDto attachmentDto : attachmentDtoList) {
            AttachVKDto attachVKDto = new AttachVKDto();
            attachVKDto.setName(attachmentDto.getName());
            attachVKDto.setId(attachmentDto.getId());
            attachVKDto.setExternalUrl(attachmentDto.getExternalUrl());

            attachVKDtos.add(attachVKDto);
        }

        return attachVKDtos;
    }

    private boolean isNeedToDelay(String publishDate) {
        Long minutes = DateUtils.getDiffDateAndCurrentDateInMinutes(publishDate);

        return minutes > vkApiSetting.getDiffMinutesBeforePost();
    }

    private UserActor getUserActor(VKUserActor vkUserActor) {
        return new UserActor(vkUserActor.getVkUserId(), vkUserActor.getVkAccessCode());
    }

    private List<VKGroup> convertToVKGroupList(List<GroupFull> groupFulls) {
        List<VKGroup> vkGroupList = new ArrayList<>(groupFulls.size());
        for (GroupFull group : groupFulls) {
            VKGroup vkGroup = new VKGroup();

            vkGroup.setVkId(Long.parseLong(group.getId()));
            vkGroup.setName(group.getName());
            vkGroup.setAvatarUrl(group.getPhoto50());
            vkGroupList.add(vkGroup);
        }
        return vkGroupList;
    }

}
