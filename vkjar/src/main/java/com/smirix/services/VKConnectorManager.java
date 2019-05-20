package com.smirix.services;

import com.smirix.entities.VKUserActor;
import com.smirix.settings.VKApiSetting;
import com.smirix.utils.StringUtils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;
import com.vk.api.sdk.queries.groups.GroupsGetFilter;
import com.vk.api.sdk.queries.photos.PhotosGetWallUploadServerQuery;
import com.vk.api.sdk.queries.photos.PhotosSaveWallPhotoQuery;
import com.vk.api.sdk.queries.upload.UploadPhotoWallQuery;
import com.vk.api.sdk.queries.wall.WallPostQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Коннектор для логина пользователем
 * Created by Smirnov Victor on 09.04.17.
 */
public class VKConnectorManager {

    private static final String VK_ERROR_MSG = "Ошибка при обращении к ВКонтакте.";
    private static final String VK_ERROR_MSG_EMPTY = VK_ERROR_MSG + " Вернулся пустой ответ UserAuthResponse";
    private static Logger LOGGER = LoggerFactory.getLogger(VKConnectorManager.class);

    @Autowired
    @Qualifier("vkApiSetting")
    private VKApiSetting vkApiSetting;

    /**
     * Получение access token пользователя ВКонтакте
     * @param code - код обмена на access token
     * @return Ответ от ВКонтакте
     */
    public UserAuthResponse authUser(String code) {

        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserAuthResponse authResponse;

        try {

            Integer applicationId = vkApiSetting.getApplicationId();
            String secretKey = vkApiSetting.getApplicationSecretKey();
            String redirectUrl = vkApiSetting.getApplicationRedirectUri();

            authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(applicationId, secretKey, redirectUrl, code)
                    .execute();

        } catch (Exception e) {
            LOGGER.error(VK_ERROR_MSG, e);
            throw new RuntimeException(VK_ERROR_MSG, e);
        }

        if (authResponse == null) {
            LOGGER.error(VK_ERROR_MSG_EMPTY);
            throw new RuntimeException(VK_ERROR_MSG_EMPTY);
        }

        return authResponse;
    }

    public List<GroupFull> getGroups(VKUserActor vkUserActor)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserActor userActor = new UserActor(vkUserActor.getVkUserId(), vkUserActor.getVkAccessCode());
        try {
            GetResponse response = vk.groups().get(userActor).filter(GroupsGetFilter.ADMIN).execute();

            return vk.groups().getById(userActor).groupIds(StringUtils.integerCollectionToListOfStrings(response.getItems())).execute();
        }
        catch (ApiException | ClientException e) {
            LOGGER.error(VK_ERROR_MSG, e);
        }
        return Collections.emptyList();
    }

    public List<GroupFull> getGroups(VKUserActor vkUserActor, Collection<Integer> ids)
    {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        UserActor userActor = new UserActor(vkUserActor.getVkUserId(), vkUserActor.getVkAccessCode());
        try {
            return vk.groups().getById(userActor).groupIds((StringUtils.integerCollectionToListOfStrings(ids))).execute();
        }
        catch (ApiException | ClientException e) {
            LOGGER.error(VK_ERROR_MSG, e);
        }
        return Collections.emptyList();
    }

    private String getUploadServerUrl(UserActor actor, Integer groupId) {
        try {
            VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
            PhotosGetWallUploadServerQuery uploadServerQuery = vk.photos().getWallUploadServer(actor).groupId(groupId);

            return uploadServerQuery.execute().getUploadUrl();
        } catch (ApiException | ClientException e) {
            LOGGER.error(VK_ERROR_MSG, e);
        }
        return null;
    }

    private WallUploadResponse getPhotoList(String uploadServerUrl, File file) {
        try {
            VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

            UploadPhotoWallQuery uploadPhotoQuery = vk.upload().photoWall(uploadServerUrl, file);
            return uploadPhotoQuery.execute();
        } catch (ApiException | ClientException e) {
            LOGGER.error(VK_ERROR_MSG, e);
        }
        return null;
    }

    public List<Photo> getNameLoadedPhoto(UserActor actor, Integer groupId, File file) {

        String uploadServerUrl = getUploadServerUrl(actor, groupId);
        WallUploadResponse wallUploadResponse = getPhotoList(uploadServerUrl, file);

        return getPhotos(actor, groupId, wallUploadResponse);
    }

    private List<Photo> getPhotos(UserActor actor, Integer groupId, WallUploadResponse wallUploadResponse) {
        try {
            VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

            PhotosSaveWallPhotoQuery photosSaveWallPhotoQuery = vk.photos()
                                                                    .saveWallPhoto(actor, wallUploadResponse.getPhoto())
                                                                    .server(wallUploadResponse.getServer())
                                                                    .hash(wallUploadResponse.getHash())
                                                                    .groupId(groupId);

            return photosSaveWallPhotoQuery.execute();

        } catch (ApiException | ClientException e) {
            LOGGER.error(VK_ERROR_MSG, e);
        }
        return null;
    }

    /**
     * Создать пост в сообществе
     * @param actor - Пользователь ВКонтакте
     * @param groupId - Идентификатор сообщества
     * @param message - Публикуемое сообщение
     * @param time - Время публикации
     * @param fromGroup - Если true, то опубликовать от имени группы. Если false, то опубликовать от имени пользователя
     * @param attachments - Вложения
     * @return Идентификатор поста
     */
    public Integer createPost(UserActor actor, Integer groupId, String message, int time, boolean fromGroup, List<String> attachments) {

        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

        try {
            WallPostQuery query = vk.wall()
                                    .post(actor)
                                    .ownerId(groupId)
                                    .fromGroup(fromGroup)
                                    .message(message);

            if (CollectionUtils.isNotEmpty(attachments)) {
                query.attachments(attachments);
            }

            if(time > 0) {
                query.publishDate(time);
            }

            return query.execute().getPostId();

        } catch (ApiException | ClientException e) {
            LOGGER.error(VK_ERROR_MSG, e);
        }

        return null;
    }

    public GroupAuthResponse authGroup(String code) {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        GroupAuthResponse authResponse;
        try {
            authResponse = vk.oauth()
                    .groupAuthorizationCodeFlow(vkApiSetting.getApplicationId(), vkApiSetting.getApplicationSecretKey(), vkApiSetting.getApplicationRedirectGroupUri(), code)
                    .execute();
        }
        catch (Exception e) {
            LOGGER.error(VK_ERROR_MSG, e);
            throw new RuntimeException(VK_ERROR_MSG, e);
        }

        if (authResponse == null) {
            LOGGER.error(VK_ERROR_MSG + " authResponse == null");
            throw new RuntimeException(VK_ERROR_MSG + "com.smirix.services.VKConnectorManager#authGroup authResponse == null");
        }

        return authResponse;
    }
}
